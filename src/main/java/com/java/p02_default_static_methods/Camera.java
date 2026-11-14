package com.java.p02_default_static_methods;

interface Camera {
    void takePhoto();
}

interface MusicPlayer {
    void playMusic();
}

class SmartPhone implements Camera, MusicPlayer {

    @Override
    public void takePhoto() {
        System.out.println("Taking photo");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music");
    }
}


//Java does not support multiple inheritance through classes.
//
//For example, this is not allowed:
//class C extends A, B {
//}
//
//Here, SmartPhone implements two interfaces.
//
//This is called multiple inheritance of type.
//But Java supports multiple inheritance through interfaces.