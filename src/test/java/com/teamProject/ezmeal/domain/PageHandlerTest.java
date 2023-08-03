package com.teamProject.ezmeal.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void test() {
        PageHandler ph = new PageHandler(250, 1);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 5);

    }
    @Test
    public void test2() {
        PageHandler ph = new PageHandler(250, 11);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEndPage() == 15);
    }

    @Test
    public void test3() {
        PageHandler ph = new PageHandler(255, 25);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getEndPage() == 25);
    }
}
//package com.teamProject.ezmeal.domain;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PageHandlerTest {
//    SearchCondition sc = new SearchCondition();
//    @Test
//    public void test() {
//        PageHandler ph = new PageHandler(250, sc);
//        ph.print();
//        System.out.println("ph = " + ph);
//
//        assertTrue(ph.getBeginPage() == 1);
//        assertTrue(ph.getEndPage() == 10);
//
//    }
//
//    @Test
//    public void test2() {
//        PageHandler ph = new PageHandler(250, sc);
//        ph.print();
//        System.out.println("ph = " + ph);
//
//        assertTrue(ph.getBeginPage() == 11);
//        assertTrue(ph.getEndPage() == 20);
//    }
//
//    @Test
//    public void test3() {
//
//        PageHandler ph = new PageHandler(255, sc);
//        ph.print();
//        System.out.println("ph = " + ph);
//
//        assertTrue(ph.getBeginPage() == 21);
//        assertTrue(ph.getEndPage() == 26);
//    }
//}