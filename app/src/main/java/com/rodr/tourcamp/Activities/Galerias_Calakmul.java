package com.rodr.tourcamp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.rodr.tourcamp.Adapter_Maps.Maps;
import com.rodr.tourcamp.R;

import java.util.ArrayList;
import java.util.List;

import ahmed.easyslider.EasySlider;
import ahmed.easyslider.SliderItem;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class Galerias_Calakmul extends AppCompatActivity {

    ImageSlider sliderHormigueroGaleria, imageSlider, sliderXpujilGaleria;
    EasySlider sliderBalamkuGaleria;
    //FlipperLayout sliderHormigueroGaleria;
    //List<SliderItem> listItem = new ArrayList<>();

    String [] subtitulos = {
            "1", "2", "3", "4", "5"
    };

    // FOTOS DE BALAMKU....
    String [] arrayGaleriaBalamku = {
            "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/1c/zona-arqueologica-de.jpg",
            "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/20/zona-arqueologica-de.jpg",
            "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/25/zona-arqueologica-de.jpg",
            "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/29/zona-arqueologica-de.jpg",
            "https://media-cdn.tripadvisor.com/media/photo-w/08/f6/42/3a/balamku.jpg"
    };

    // FOTOS DE HORMIGUERO....
    String [] arrayGaleriaHormiguero = {
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/03/e4/01/18/hormiguero.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/03/e4/01/18/hormiguero.jpg?w=2000&h=-1&s=1 2x",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/0d/8d/08/hormiguero.jpg?w=800&h=-1&s=1",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/2f/a3/b9/hormiguero.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/2f/a3/b9/hormiguero.jpg?w=1400&h=-1&s=1 2x",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/09/d0/ac/20160103-110155-largejpg.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/09/d0/ac/20160103-110155-largejpg.jpg?w=1400&h=-1&s=1 2x"
    };

    // FOTOS DE XPUJIL....
    String [] arrayGaleriaXpujil = {
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/2e/cb/3e/xpujil.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/2e/cb/3e/xpujil.jpg?w=1400&h=-1&s=1 2x",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/a2/a6/20/dsc-4006-largejpg.jpg?w=1200&h=-1&s=1%201x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/a2/a6/20/dsc-4006-largejpg.jpg?w=1400&h=-1&s=1%202x",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/a2/a6/1f/dsc-3993-largejpg.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/a2/a6/1f/dsc-3993-largejpg.jpg?w=1400&h=-1&s=1 2x",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/a2/a6/1d/dsc-3973-largejpg.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/a2/a6/1d/dsc-3973-largejpg.jpg?w=1400&h=-1&s=1 2x",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/2d/39/8c/xpuhil.jpg?w=1200&h=-1&s=1 1x,https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/2d/39/8c/xpuhil.jpg?w=2000&h=-1&s=1 2x"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerias_calakmul);
        initGui();
        //cargarDatosSlider();
        cargarDatosSliderBalamku();
        cargarDatosSliderHormiguero();
        cargarDatosSliderXpuhil();
    }

    private void initGui(){
        //imageSlider = (ImageSlider) findViewById(R.id.image_slider);
        sliderBalamkuGaleria = (EasySlider) findViewById(R.id.slider_Balamku_Galeria);
        sliderHormigueroGaleria = (ImageSlider) findViewById(R.id.slider_Hormiguero_Galeria);
        //sliderHormigueroGaleria = (FlipperLayout) findViewById(R.id.slider_Hormiguero_Galeria);
        sliderXpujilGaleria = (ImageSlider) findViewById(R.id.slider_Xpujil_Galeria);
    }

    /*
    private void cargarDatosSlider(){
        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel("https://media-cdn.tripadvisor.com/media/photo-w/08/f6/42/3a/balamku.jpg", null));
        imageList.add(new SlideModel("https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/29/zona-arqueologica-de.jpg", null));
        imageList.add(new SlideModel("https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/18/zona-arqueologica-de.jpg", null));

        imageSlider.setImageList(imageList);
    }*/

    private void cargarDatosSliderBalamku(){
        List<SliderItem> listItemBalamku = new ArrayList<>();

        /*listItemBalamku.add(new SliderItem("1", "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/1c/zona-arqueologica-de.jpg"));
        listItemBalamku.add(new SliderItem("1", "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/20/zona-arqueologica-de.jpg"));
        listItemBalamku.add(new SliderItem("1", "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/25/zona-arqueologica-de.jpg"));
        listItemBalamku.add(new SliderItem("1", "https://media-cdn.tripadvisor.com/media/photo-w/1d/a0/97/29/zona-arqueologica-de.jpg"));
        listItemBalamku.add(new SliderItem("1", "https://media-cdn.tripadvisor.com/media/photo-w/08/f6/42/3a/balamku.jpg"));
        */
        // LLENARLO MEEDIANTE UN CICLO....
        for (int i = 0; i < arrayGaleriaBalamku.length; i++){
            listItemBalamku.add(new SliderItem(subtitulos[i],  arrayGaleriaBalamku[i]));
        }

        sliderBalamkuGaleria.setPages(listItemBalamku);
    }

    private void cargarDatosSliderHormiguero(){
        ArrayList<SlideModel> imageList = new ArrayList<>();

        for (int i = 0; i < arrayGaleriaHormiguero.length; i++){
            imageList.add(new SlideModel(arrayGaleriaHormiguero[i], null));
        }

        sliderHormigueroGaleria.setImageList(imageList);
    }

    private void cargarDatosSliderXpuhil(){
        ArrayList<SlideModel> imageList = new ArrayList<>();

        for (int i = 0; i < arrayGaleriaXpujil.length; i++){
            imageList.add(new SlideModel(arrayGaleriaXpujil[i], null));
        }

        sliderXpujilGaleria.setImageList(imageList);
    }

    private void cargarDatosSliderBecan(){
    }

    private void cargarDatosSliderCalakmul(){
    }
}