/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.c2technology.roguezombie.world;

/**
 * Thrown when a something would have been spawn, but was prevented.
 *
 * @author cryan
 */
public class UnspawnableException extends Exception {

    public UnspawnableException(String message) {
        super(message);
    }

}
