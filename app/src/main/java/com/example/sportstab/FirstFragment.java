package com.example.sportstab;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.N)
public class FirstFragment<horizontalCalendar, root> extends Fragment implements DatePickerListener {

    private List<Product_List> product_lists;
    ArrayList<Product_List> resultList = new ArrayList<>();
    private RecyclerView recyclerView;
    ProductAdapter adapter;
    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    public static FavoriteDatabase favoriteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_first, container, false);

        selectDate = root.findViewById(R.id.btnDate);
        date = root.findViewById(R.id.tvSelectedDate);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        product_lists = new ArrayList<>();


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        date.setText(year + "년 " + month + "월 " + day + "일");
                        date.setText(year + "년 " + (month + 1) + "월 " + day + "일");

                        Log.e("TEST", String.valueOf(product_lists.size()));
                        getData();
                        resultList.clear();
                        for (int i = 0; i < product_lists.size(); i++) {
                            if (product_lists.get(i).getDate() == day) {
                                resultList.add(product_lists.get(i));
                                Log.e("nalzza equal", "ENTER");
                            }
                        }
                        if (resultList.size() == 0) {
                            Toast.makeText(getContext(), "해당 날짜에 경기 일정이 없습니다.", Toast.LENGTH_SHORT).show();
//                            getData();
                            adapter.notifyDataSetChanged();
                            return;
                        }
                        adapter.nalzza(resultList);


                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();


            }
        });

        favoriteDatabase = Room.databaseBuilder(getContext(), FavoriteDatabase.class, "myfavdb").allowMainThreadQueries().build();


        getData();
        setupData(product_lists);

        return root;
    }

    private void getData() {

        product_lists.clear();
        product_lists.add(new Product_List(1,12,  "스페인 프리메라리가", "세비야 2:0 레알 베티스", "2020.06.12 05:00", "https://pngimage.net/wp-content/uploads/2018/06/sevilla-fc-png-8.png",
                "https://pngimage.net/wp-content/uploads/2018/06/real-betis-logo-png-.png"));

        product_lists.add(new Product_List(2,13, "스페인 프리메라리가", "그라나다 2:1 헤타페", "2020.06.13 02:30", "https://pngimage.net/wp-content/uploads/2018/06/granada-cf-png.png",
                "https://pngimage.net/wp-content/uploads/2018/06/getafe-png-6.png"));

        product_lists.add(new Product_List(3,13, "   독일 분데스리가", "호펜하임 0:2 라이프치히", "2020.06.13 03:30", "https://logodownload.org/wp-content/uploads/2019/12/hoffenheim-logo.png",
                "https://logodownload.org/wp-content/uploads/2019/11/rb-leipzig-logo.png"));
        product_lists.add(new Product_List(4,13, "스페인 프리메라리가", "  발렌시아 1:1 레반테", "2020.06.13 05:00", "https://pngimage.net/wp-content/uploads/2018/06/valencia-cf-png-7.png",
                "https://www.logofootball.net/wp-content/uploads/Levante-UD-HD-Logo.png"));
        product_lists.add(new Product_List(5,13, "스페인 프리메라리가", " 에스파뇰 2:0 알라베스", "2020.06.13 21:00", "https://pngimage.net/wp-content/uploads/2018/06/rcd-espanyol-png-3.png",
                "https://pngimage.net/wp-content/uploads/2018/05/alaves-logo-png-6.png"));
        product_lists.add(new Product_List(6,13, "   독일 분데스리가", "뒤셀도르프 0:1 도르트문트", "2020.06.13 22:30", "https://logodownload.org/wp-content/uploads/2019/12/fortuna-dusseldorf-logo.png",
                "https://logodownload.org/wp-content/uploads/2017/02/bvb-borussia-dortmund-logo.png"));
        product_lists.add(new Product_List(7,14, "스페인 프리메라리가", "마요르카 0:4 바르셀로나", "2020.06.14 05:00", "https://pngimage.net/wp-content/uploads/2018/06/rcd-mallorca-png-5.png",
                "https://logodownload.org/wp-content/uploads/2015/05/Barcelona-logo-escudo-4.png"));
        product_lists.add(new Product_List(8,15, "스페인 프리메라리가", "레알 마드리드 3:1 에이바르", "2020.06.15 02:30", "https://pngimage.net/wp-content/uploads/2018/06/simbolo-real-madrid-png-.png",
                "https://pngimage.net/wp-content/uploads/2018/05/eibar-png-2.png"));
        product_lists.add(new Product_List(9,20, "잉글랜드 프리미어리그", "토트넘 1:1 맨유", "2020.06.20 04:15", "https://logodownload.org/wp-content/uploads/2018/11/tottenham-logo-escudo.png",
                "https://logodownload.org/wp-content/uploads/2016/10/Manchester-United-logo-escudo-2.png"));
        product_lists.add(new Product_List(10,24, "스페인 프리메라리가", "바르셀로나 vs 아틀레틱", "2020.06.24 05:00", "https://logodownload.org/wp-content/uploads/2015/05/Barcelona-logo-escudo-4.png",
                "https://pngimage.net/wp-content/uploads/2018/05/escudo-athletic-de-bilbao-png-2.png"));
        product_lists.add(new Product_List(11,25, "잉글랜드 프리미어리그", "     맨유 vs 셰필드", "2020.06.25 02:00", "https://logodownload.org/wp-content/uploads/2016/10/Manchester-United-logo-escudo-2.png",
                "https://logodownload.org/wp-content/uploads/2019/10/sheffield-united-logo-1.png"));
        product_lists.add(new Product_List(12,25, "잉글랜드 프리미어리그", "     리버풀 vs 팰리스", "2020.06.25 04:15", "https://logodownload.org/wp-content/uploads/2017/02/liverpool-fc-logo-escudo-8.png",
                "https://1000marcas.net/wp-content/uploads/2020/03/Crystal-Palace-Logo-PNG.png"));


    }

    private void setupData(List<Product_List> product_lists) {
        adapter = new ProductAdapter(product_lists, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDateSelected(DateTime dateSelected) {
        Toast.makeText(getContext(), dateSelected.toString(), Toast.LENGTH_SHORT).show();
    }
}