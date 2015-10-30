/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;


public class StatusBreakdownResponse {
    private int pending;
    private int reviewed;

    public StatusBreakdownResponse() {
    }

    public StatusBreakdownResponse(int pending, int reviewed) {
        this.pending = pending;
        this.reviewed = reviewed;
    }

    public int getPending() {
        return pending;
    }

    public int getReviewed() {
        return reviewed;
    }
}
