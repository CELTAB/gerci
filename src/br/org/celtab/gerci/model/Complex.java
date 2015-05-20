package br.org.celtab.gerci.model;

public class Complex {


	private double m_Real;
	private double m_Imaginary;


	public Complex() {
		m_Real = 0D;
		m_Imaginary = 0D;
	}

	public Complex(double Real, double imaginary){
		m_Real = Real;
		m_Imaginary = imaginary;
	}

	public double getReal() {
		return m_Real;
	}
	
	public void setReal(double real) {
		m_Real = real;
	}
	
	public double getImaginary() {
		return m_Imaginary;
	}
	
	public void setImaginary(double imaginary) {
		m_Imaginary = imaginary;
	}
	 
	public boolean equals(Complex num) {
		return (m_Real == num.getReal() && m_Imaginary == num.getImaginary());
	}
	
	public void let(Complex num) {
		
		m_Real = num.getReal();
		m_Imaginary = num.getImaginary(); 
	}
	       
	public double abs() {
		return (Math.sqrt(m_Real * m_Real + m_Imaginary * m_Imaginary));
	}

	// Argument of a complex number in radians
	public double arg() {

		double r = 0;

	    if (m_Real != 0) 
	    	r = Math.atan(m_Imaginary / m_Real);

	    return (r);

	}

	// Argument of a complex number in degree
	public double ArgDeg() {
		return (180D / Math.PI) * this.arg();
	}

	// overridden ToString to return format: a + bi
	@Override
	public String toString() {

		String r;

         if (m_Real >= 0) {
        	 r = " " + m_Real;
         } else {
        	 r = ""  + m_Real;
         }


         if (m_Imaginary >= 0) {
        	 r += " + " + imaginaryToString(m_Imaginary);
         } else {
        	 r += " - " + imaginaryToString(-m_Imaginary);
         }

         return r + "i";
         
	}
	 
	public String imaginaryToString(double v) {

		if (v == 1) {
			return ""; 
		} else {
			return ""+v;
		}
	}


	  // ToString Gaussian to return format: (a, b)

	  public String toStringGaussian() {
		 return "("+m_Real+", "+m_Imaginary+")";
	  }
	  
	  public static Complex soma(Complex a, int b) {
		  return new Complex(a.getReal()+b, a.getImaginary()+b);
	  }

	  public static Complex soma(Complex a, Complex b) {
		  return new Complex(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
	  }
	       

	  public static Complex subtracao(Complex a) {
		  return new Complex(-a.getReal(), -a.getImaginary());
	  }
	  
	  public static Complex subtracao(Complex a, Complex b) {
		  return new Complex(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary());
	  }

	  public static Complex multiplicacao(Complex a, Complex b) {
		  return new Complex( a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary(), a.getReal() * b.getImaginary() + b.getReal() * a.getImaginary()
				  	  );
	  }

	  public static Complex divisao(Complex a, Complex b) {
		  
		  double c1, c2, d;

          d = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();

          if (d == 0) {
              return new Complex(0,0);
          } else {

              c1 = a.getReal() * b.getReal() + a.getImaginary() * b.getImaginary();

              c2 = a.getImaginary() * b.getReal() - a.getReal() * b.getImaginary();

              return new Complex(c1 / d, c2 / d);

          }
          
	  }

	  public static Complex divisao(Complex a, double b) {
		  return new Complex(a.getReal() / b, a.getImaginary() / b);
	  }

	
}
