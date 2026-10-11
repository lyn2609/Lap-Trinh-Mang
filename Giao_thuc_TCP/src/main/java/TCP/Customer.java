/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.Serializable;



/**
 *
 * @author ADMIN
 */
public class Customer implements Serializable{
    private static final long serialVersionUID = 20170711L;
    public int id;
    public String name,code, dayOfBirth, userName;
    public Customer(int id, String name, String code, String dayOfBirth, String userName){
        this.id = id;
        this.name = name;
        this.code = code;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }
}
