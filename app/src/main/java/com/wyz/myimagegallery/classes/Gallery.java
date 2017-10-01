package com.wyz.myimagegallery.classes;

import android.graphics.Bitmap;

/**
 * Created by W€µÐr€Y™ on 04/06/2017.
 */

public class Gallery {

        private int ID;
        private String titulo;
        private String detalhes;
        private String imagem;


        public Gallery() {
        }

        public Gallery(String titulo, String detalhes) {
            this.titulo = titulo;
            this.detalhes = detalhes;


        }
        public Gallery(String titulo, String detalhes, String imagem) {
            this.titulo = titulo;
            this.detalhes = detalhes;
            this.imagem = imagem;


        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDetalhes() {
            return detalhes;
        }

        public void setDetalhes(String detalhes) {
            this.detalhes = detalhes;
        }

        public String getImagem() {
            return imagem;
        }

        public void setImagem(String imagem) {
            this.imagem = imagem;
        }

    @Override
    public String toString() {
        return "GALERIA:\n" +
                "TITULO=" + titulo + "\n" +
                "DETALHES=" + detalhes + "\n" +
                "IMAGEM=" + imagem + "\n";
    }

}
