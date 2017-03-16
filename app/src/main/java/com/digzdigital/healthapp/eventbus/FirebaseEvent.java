package com.digzdigital.healthapp.eventbus;

/**
 * Created by Digz on 10/03/2017.
 */

public class FirebaseEvent {

    public final EventType type;

    public FirebaseEvent(EventType type){
        this.type = type;
    }
}
