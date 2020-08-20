package com.example.sportstab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ZeroFragment extends Fragment {

    public ZeroFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    ArrayList<Item> items= new ArrayList<>();
    MyAdapter adapter;

    SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zero, container, false);

        recyclerView=view.findViewById(R.id.recycler);
        adapter= new MyAdapter(items,this.getContext());
        recyclerView.setAdapter(adapter);

        //리사이클러의 배치관리자 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //대량의 데이터 추가 작업
        readRss();

        refreshLayout= view.findViewById(R.id.layout_swipe);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                adapter.notifyDataSetChanged();
                readRss();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    void readRss(){

        try {
            URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query=%EC%B6%95%EA%B5%AC%EB%89%B4%EC%8A%A4&field=0&nx_search_query=&nx_and_query=&nx_sub_query=&nx_search_hlquery=&is_dts=0");

            //스트림역할하여 데이터 읽어오기 : 인터넷 작업은 반드시 퍼미션 작성해야함.
            //Network작업은 반드시 별도의 Thread만 할 수 있다.
            //별도의 Thread 객체 생성
            RssFeedTask task= new RssFeedTask();
            task.execute(url); //doInBackground()메소드가 발동[thread의 start()와 같은 역할]
        } catch (MalformedURLException e) { e.printStackTrace();}

    }// readRss Method ..

    //이너 클래스
    class RssFeedTask extends AsyncTask<URL, Void, String> {

        //Thread의 run()메소드와 같은 역할
        @Override
        protected String doInBackground(URL... urls) { //...는 여러개를 받는 의미, 만약 task.execute(url, url2, url3); 보내면 urls[3]로 받는다.
            //전달받은 URL 객체
            URL url= urls[0];

            //해임달(URL)에게 무지개로드(Stream) 열도록..
            try {
                InputStream is= url.openStream();

                //읽어온 xml를 파싱(분석)해주는 객체 생성
                XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                XmlPullParser xpp= factory.newPullParser();

                //utf-8은 한글도 읽어오기 위한 인코딩 방식
                xpp.setInput(is, "utf-8");
                int eventType= xpp.getEventType();

                Item item= null;
                String tagName= null;

                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tagName=xpp.getName();

                            if(tagName.equals("item")){
                                item= new Item();
                            }else if(tagName.equals("title")){
                                xpp.next();
                                if(item!=null) item.setTitle(xpp.getText());
                            }else if(tagName.equals("link")){
                                xpp.next();
                                if(item!=null) item.setLink(xpp.getText());
                            }else if(tagName.equals("description")){
                                xpp.next();
                                if(item!=null) item.setDesc(xpp.getText());
                            }else if(tagName.equals("media:thumbnail")){
                                xpp.next();
                                if(item!=null) item.setImgUrl(xpp.getText());
                            }else if(tagName.equals("pubDate")){
                                xpp.next();
                                if(item!=null) item.setDate(xpp.getText());
                            }
                            break;
                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tagName=xpp.getName();
                            if(tagName.equals("item")){
                                //읽어온 기사 한개를 대량의 데이터에 추가
                                items.add(item);
                                item=null;

                                //리사이클러의 아답터에게 데이터가
                                //변경되었다는 것을 통지
                                publishProgress();
                            }
                            break;
                    }
                    eventType= xpp.next();
                }//while

                //파싱 작업이 완료되었다!!
                //테스트로 Toastㄹ 보여주기, 단 별도 스레드는
                //UI작업이 불가! 그래서 runOnUiThread()를 이용했었음.
                //이 UI작업을 하는 별도의 메소드로
                //결과를 리턴하는 방식을 사용

            } catch (IOException e) {e.printStackTrace();} catch (XmlPullParserException e) {e.printStackTrace();}

            return "파싱종료"; // 리턴 값은 onPostExecute(String s) s에 전달된다.
        }//doIBackground()

        @Override
        protected void onProgressUpdate(Void...values) {
            super.onProgressUpdate(values);

            adapter.notifyItemInserted(items.size());
        }

        //doInBackground메소드가 종료된 후
        //UI작업을 위해 자동 실행되는 메소드
        //runOnUiThread()와 비슷한 역할
        @Override
        protected void onPostExecute(String s) { //매개 변수 s에 들어오는 값음 doIBackground()의 return 값이다.
            super.onPostExecute(s);

            refreshLayout.setRefreshing(false);

            adapter.notifyDataSetChanged();

            //이 메소드 안에서는 UI변경 작업 가능
            Toast.makeText(getActivity(), s+":"+items.size(), Toast.LENGTH_SHORT).show();
        }
    }//RssFeedTask class

}//MainActivity class ..
