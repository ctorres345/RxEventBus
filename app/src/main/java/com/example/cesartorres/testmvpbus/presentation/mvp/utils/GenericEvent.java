package com.example.cesartorres.testmvpbus.presentation.mvp.utils;

/**
 * Created by darryl on 9/8/17.
 */

public class GenericEvent {

    public String eventName;
    public String message;
    public Object mainObject;
    public Object secondObject;

    public GenericEvent(String eventName, String message, Object mainObject, Object secondObject) {
        this.eventName = eventName;
        this.message = message;
        this.mainObject = mainObject;
        this.secondObject = secondObject;
    }

    public GenericEvent(String eventName) {
        this.eventName = eventName;
    }

    public GenericEvent(String eventName, Object mainObject) {
        this.eventName = eventName;
        this.mainObject = mainObject;
    }

    public GenericEvent(String eventName, String message) {
        this.eventName = eventName;
        this.message = message;
    }

    public GenericEvent(String eventName, String message, Object mainObject) {

        this.eventName = eventName;
        this.message = message;
        this.mainObject = mainObject;
    }

    @Override
    public String toString() {
        return "GenericEvent{" +
                "eventName='" + eventName + '\'' +
                ", message='" + message + '\'' +
                ", mainObject=" + mainObject +
                ", secondObject=" + secondObject +
                '}';
    }

}
