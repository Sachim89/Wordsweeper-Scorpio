package com.scorpio.server.model;

import com.scorpio.server.accessory.Coordinate;

public class Tile implements IModel{
    private Coordinate location;

    private char contents;
    private int poits;
    private int multiplier;

    private int sharedBy;
}