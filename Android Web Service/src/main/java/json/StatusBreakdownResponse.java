/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author andrew.lim.2013
 */
public class StatusBreakdownResponse {
    private int submitted;
    private int reviewed;
    private int confirmed;
    private int rejected;

    public StatusBreakdownResponse() {
    }

    public StatusBreakdownResponse(int submitted, int reviewed, int confirmed, int rejected) {
        this.submitted = submitted;
        this.reviewed = reviewed;
        this.confirmed = confirmed;
        this.rejected = rejected;
    }

    public int getSubmitted() {
        return submitted;
    }

    public int getReviewed() {
        return reviewed;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getRejected() {
        return rejected;
    }    
}
