package com.laioffer.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;// naController指挥者，来指挥页面跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);//这里是找reference, 这个是紫色的navigation bar
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);//找reference这是每个fragement的白色主体页面
        navController = navHostFragment.getNavController();//把navcontroller负责fragment切换
        NavigationUI.setupWithNavController(navView, navController);// 这是把下面的三个紫色的navigation bar和fragment相连接;
        //NavigationUI.setupActionBarWithNavController(this, navController);//这一行是在每一个navigation界面上更新title的；上面的title就叫做action bar;



//        NewsApi api = RetrofitClient.newInstance(this).create(NewsApi.class);
//        api.getTopHeadlines("US").enqueue(new Callback<NewsResponse>() {
//        @Override
//        public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                           if (response.isSuccessful()) {
//                                   Log.d("getTopHeadlines", response.body().toString());
//                               } else {
//                                   Log.d("getTopHeadlines", response.toString());
//                               }
//                       }
//         @Override
//         public void onFailure(Call<NewsResponse> call, Throwable t) {
//                           Log.d("getTopHeadlines", t.toString());
//         }
//        });
    }
   @Override
    public boolean onSupportNavigateUp() {//这里是控制回撤箭头回到homepage主页面;
               return navController.navigateUp();
    }
}
