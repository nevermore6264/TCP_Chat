/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;


/**
 *
 * @author Admin
 */
public class MessageView {
    private Integer total;
    private List<MessageForm> items;

    public MessageView() {
    }

    public MessageView(Integer total, List<MessageForm> items) {
        this.total = total;
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<MessageForm> getItems() {
        return items;
    }

    public void setItems(List<MessageForm> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MessageView{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}