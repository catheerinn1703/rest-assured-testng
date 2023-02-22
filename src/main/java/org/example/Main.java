package org.example;

import org.testng.annotations.Test;

public class Main {
    @Test(priority = 2)
    public void testSetup() {
        System.out.println("This is test setup");
    }

    @Test(priority = 1)
    public void testDemo() {
        System.out.println("This is t est demo");
    }

}
