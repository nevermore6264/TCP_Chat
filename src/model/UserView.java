/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.User;


/**
 *
 * @author Admin
 */
public class UserView {
    private Integer total;
    private List<User> items;

    public UserView() {
    }

    public UserView(Integer total, List<User> items) {
        this.total = total;
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}