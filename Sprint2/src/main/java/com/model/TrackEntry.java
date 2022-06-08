package com.model;

import com.model.enumerable.TrackAction;

import java.time.LocalDateTime;

public class TrackEntry extends AbstractEntity{
    protected LocalDateTime date;
    private Evidence evidence;
    private Detective detective;
    private TrackAction action;
    private String reason;
}
