package com.tedneward.example;

import java.beans.*;
import java.util.*;
import java.util.Comparator;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn = "";
  private boolean propertyChangeFired = false;

  private static int num = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;

    num++;
  }

  public void setAge(int value) throws IllegalArgumentException{
    if(value < 0)
      throw new IllegalArgumentException();
    this.age = value;
  }

  public int getAge(){
    return this.age;
  }

  public void setName(String value) throws IllegalArgumentException{
    if(value == null)
      throw new IllegalArgumentException();
    this.name = value;
  }

  public String getName(){
    return this.name;
  }

  public void setSalary(double value){
    this.salary = value;
  }

  public double getSalary(){
    return this.salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public String getSSN(){
    return this.ssn;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + 
    this.salary + "]";
  }

  public static int count(){
    return num;
  }

  public boolean equals(Object o){
    if(!(o instanceof Person))
      return false;
    Person p = (Person) o;
    if(p.getName().equals(this.name)&&p.getAge()==this.age)
      return true;
    return false;
  }

  public static class AgeComparator implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2){
      if(p1.getAge()>p2.getAge()) 
        return 1;
      else if(p1.getAge()<p2.getAge())
        return -1;
      else
        return 0;
    }
  }

  @Override
  public int compareTo(Person p){
    if(this.salary>p.getSalary()) 
      return -1;
    else if(this.salary<p.getSalary())
      return 1;
    else
      return 0;
  }

  public static List<Person> getNewardFamily(){
    List<Person> list = new ArrayList<Person>();
    list.add(new Person("Ted", 41, 250000));
    list.add(new Person("Charlotte", 43, 150000));
    list.add(new Person("Michael", 22, 10000));
    list.add(new Person("Matthew", 15, 0));
    return list;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
