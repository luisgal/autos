package com.example.autos;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encode {
    @Test
    public void ByCrypt(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("6Oql&767Iw73"));
        System.out.println(encoder.encode("Kvi3dHQ!t7s2"));
        System.out.println(encoder.encode("fwR39M8$3AD0"));
        System.out.println(encoder.encode("6z3&5863CJzg"));
        System.out.println(encoder.encode("eSa3#985f2F@"));
        System.out.println(encoder.encode("e81s2$H*XVqX"));
        System.out.println(encoder.encode("zlTW3LP06S#n"));
        System.out.println(encoder.encode("zezTeWR%28@1"));
        System.out.println(encoder.encode("$82wS79v0t&*"));
        System.out.println(encoder.encode("yMlq648hcg$X"));


    }
}
