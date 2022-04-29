package br.com.fagner.math;

import br.com.fagner.converters.NumberConverter;
import br.com.fagner.exception.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SimpleMath {

    public Double sum( Double numberOne, Double numberTwo) {
            return  numberOne + numberTwo;
    }

    public Double multiplication( Double numberOne, Double numberTwo) {
        return  numberOne * numberTwo;
    }

    public Double subtraction( Double numberOne, Double numberTwo) {
        return  numberOne - numberTwo;
    }


    public Double division( Double numberOne, Double numberTwo) {
        return  numberOne / numberTwo;
    }

     public Double mean( Double numberOne, Double numberTwo) {
         return  (numberOne + numberTwo) / 2;
     }

      public Double squareRoot( Double numberOne) {
          return   (Double) Math.sqrt(numberOne);
      }


}
