package interp;

import static java.lang.Math.*;

/*
 * This class was derived in part from software in the Boost C++ library, 
 * use of which is governed by the following license:
 *
 * Boost Software License - Version 1.0 - August 17th, 2003
 * 
 * Permission is hereby granted, free of charge, to any person or organization
 * obtaining a copy of the software and accompanying documentation covered by
 * this license (the "Software") to use, reproduce, display, distribute,
 * execute, and transmit the Software, and to prepare derivative works of the
 * Software, and to permit third-parties to whom the Software is furnished to
 * do so, all subject to the following:
 * 
 * The copyright notices in the Software and this entire statement, including
 * the above license grant, this restriction and the following disclaimer,
 * must be included in all copies of the Software, in whole or in part, and
 * all derivative works of the Software, unless such copies or derivative
 * works are solely in the form of machine-executable object code generated by
 * a source language processor.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDERS OR ANYONE DISTRIBUTING THE SOFTWARE BE LIABLE
 * FOR ANY DAMAGES OR OTHER LIABILITY, WHETHER IN CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

/**
 * Special mathematical functions.
 * Adapted from Boost C++ libraries.
 * @author Dave Hale, Colorado School of Mines.
 */
public class SpecialMath {

  /**
   * Evaluates the modified Bessel function of the second kind and order 0.
   * @param n order.
   * @return the function value.
   */
  public static double k0(double x) {
    return K0.k0(x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order 0.
   * @param n order.
   * @return the function value.
   */
  public static float k0(float x) {
    return (float)K0.k0(x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order 1.
   * @param n order.
   * @return the function value.
   */
  public static double k1(double x) {
    return K1.k1(x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order 1.
   * @param n order.
   * @return the function value.
   */
  public static float k1(float x) {
    return (float)K1.k1(x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order n.
   * @param n order.
   * @return the function value.
   */
  public static double kn(int n, double x) {
    return KN.kn(n,x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order n.
   * @param n order.
   * @return the function value.
   */
  public static float kn(int n, float x) {
    return (float)KN.kn(n,x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order n+1/2.
   * @param n order minus 1/2.
   * @return the function value.
   */
  public static double kn2(int n, double x) {
    return KN2.kn2(n,x);
  }

  /**
   * Evaluates the modified Bessel function of the second kind and order n+1/2.
   * @param n order minus 1/2.
   * @return the function value.
   */
  public static float kn2(int n, float x) {
    return (float)KN2.kn2(n,x);
  }

  ///////////////////////////////////////////////////////////////////////////
  // private

  /**
   * Adapted by Dave Hale from boost::math::bessel_k0
   * Copyright (c) 2006 Xiaogang Zhang
   * Modified Bessel function of the second kind of order zero
   * minimax rational approximations on intervals, see
   * Russon and Blair, Chalk River Report AECL-3461, 1969
   */
  private static class K0 {
    static final double[] P1 = {
       2.4708152720399552679e+03,
       5.9169059852270512312e+03,
       4.6850901201934832188e+02,
       1.1999463724910714109e+01,
       1.3166052564989571850e-01,
       5.8599221412826100000e-04
    };
    static final double[] Q1 = {
       2.1312714303849120380e+04,
      -2.4994418972832303646e+02,
       1.0
    };
    static final double[] P2 = {
      -1.6128136304458193998e+06,
      -3.7333769444840079748e+05,
      -1.7984434409411765813e+04,
      -2.9501657892958843865e+02,
      -1.6414452837299064100e+00
    };
    static final double[] Q2 = {
      -1.6128136304458193998e+06,
       2.9865713163054025489e+04,
      -2.5064972445877992730e+02,
       1.0
    };
    static final double[] P3 = {
       1.1600249425076035558e+02,
       2.3444738764199315021e+03,
       1.8321525870183537725e+04,
       7.1557062783764037541e+04,
       1.5097646353289914539e+05,
       1.7398867902565686251e+05,
       1.0577068948034021957e+05,
       3.1075408980684392399e+04,
       3.6832589957340267940e+03,
       1.1394980557384778174e+02
    };
    static final double[] Q3 = {
       9.2556599177304839811e+01,
       1.8821890840982713696e+03,
       1.4847228371802360957e+04,
       5.8824616785857027752e+04,
       1.2689839587977598727e+05,
       1.5144644673520157801e+05,
       9.7418829762268075784e+04,
       3.1474655750295278825e+04,
       4.4329628889746408858e+03,
       2.0013443064949242491e+02,
       1.0
    };
    static double k0(double x) {
      if (x<0.0)
        return Double.NaN;
      if (x==0.0f)
        return Double.POSITIVE_INFINITY;
      if (x<=1.0) {
        double y = x*x;
        double r1 = poly(P1,y)/poly(Q1,y);
        double r2 = poly(P2,y)/poly(Q2,y);
        double factor = log(x);
        return r1-factor*r2;
      } else {
        double y = 1.0/x;
        double r = poly(P3,y)/poly(Q3,y);
        double factor = exp(-x)/sqrt(x);
        return factor*r;
      }
    }
  }

  /**
   * Adapted from boost::math::bessel_k1
   * Copyright (c) 2006 Xiaogang Zhang
   * Modified Bessel function of the second kind of order one
   * minimax rational approximations on intervals, see
   * Russon and Blair, Chalk River Report AECL-3461, 1969
   */
  private static class K1 {
    static final double[] P1 = {
      -2.2149374878243304548e+06,
       7.1938920065420586101e+05,
       1.7733324035147015630e+05,
       7.1885382604084798576e+03,
       9.9991373567429309922e+01,
       4.8127070456878442310e-01
    };
    static final double[] Q1 = {
      -2.2149374878243304548e+06,
       3.7264298672067697862e+04,
      -2.8143915754538725829e+02,
       1.0
    };
    static final double[] P2 = {
       0.0,
      -1.3531161492785421328e+06,
      -1.4758069205414222471e+05,
      -4.5051623763436087023e+03,
      -5.3103913335180275253e+01,
      -2.2795590826955002390e-01
    };
    static final double[] Q2 = {
      -2.7062322985570842656e+06,
       4.3117653211351080007e+04,
      -3.0507151578787595807e+02,
       1.0
    };
    static final double[] P3 = {
       2.2196792496874548962e+00,
       4.4137176114230414036e+01,
       3.4122953486801312910e+02,
       1.3319486433183221990e+03,
       2.8590657697910288226e+03,
       3.4540675585544584407e+03,
       2.3123742209168871550e+03,
       8.1094256146537402173e+02,
       1.3182609918569941308e+02,
       7.5584584631176030810e+00,
       6.4257745859173138767e-02
    };
    static final double[] Q3 = {
       1.7710478032601086579e+00,
       3.4552228452758912848e+01,
       2.5951223655579051357e+02,
       9.6929165726802648634e+02,
       1.9448440788918006154e+03,
       2.1181000487171943810e+03,
       1.2082692316002348638e+03,
       3.3031020088765390854e+02,
       3.6001069306861518855e+01,
       1.0
    };
    static double k1(double x) {
      if (x<0.0)
        return Double.NaN;
      if (x==0.0f)
        return Double.POSITIVE_INFINITY;
      if (x<=1.0) {
        double y = x*x;
        double r1 = poly(P1,y)/poly(Q1,y);
        double r2 = poly(P2,y)/poly(Q2,y);
        double factor = log(x);
        return (r1+factor*r2)/x;
      } else {
        double y = 1.0/x;
        double r = poly(P3,y)/poly(Q3,y);
        double factor = exp(-x)/sqrt(x);
        return factor*r;
      }
    }
  }

  /**
   * Adapted from boost::math::bessel_kn
   * Copyright (c) 2006 Xiaogang Zhang
   * Modified Bessel function of the second kind of integer order
   * K_n(z) is the dominant solution, forward recurrence always OK 
   * (though unstable)
   */
  private static class KN {
    static double kn(int n, double x) {
      if (x<0.0)
        return Double.NaN;
      if (x==0.0)
        return Double.POSITIVE_INFINITY;
      if (n<0)
        n = -n;
      if (n==0)
        return K0.k0(x);
      if (n==1)
        return K1.k1(x);
      double prev = k0(x);
      double curr = k1(x);
      int k = 1;
      double scale = 1.0;
      double value;
      do {
        double fact = 2.0*k/x;
        if ((Double.MAX_VALUE-abs(prev))/fact<abs(curr)) {
          scale /= curr;
          prev /= curr;
          curr = 1.0;
        }
        value = fact*curr+prev;
        prev = curr;
        curr = value;
        ++k;
      } while (k<n);
      if (Double.MAX_VALUE*scale<abs(value))
        return sign(scale)*sign(value)*Double.POSITIVE_INFINITY;
      value /= scale;
      return value;
    }
  }

  /**
   * Modified Bessel function of the second kind and order n+1/2.
   * odd-half-integer order v = 1/2, 3/2, 5/2, ....
   * Dave Hale, Colorado School of Mines
   */ 
  private static class KN2 {
    static double kn2(int n, double x) {
      if (x<0.0)
        return Double.NaN;
      if (x==0.0)
        return Double.POSITIVE_INFINITY;
      double value = sqrt(0.5*PI/x)*exp(-x);
      if (n==0)
        return value;
      if (n<0)
        n = -n-1;
      double facn = 1.0;
      double facd = 1.0;
      double facx = 0.5/x;
      double powx = 1.0;
      double sum = 1.0;
      for (int j=1; j<=n; ++j) {
        facn *= (n+j)*(n+1-j);
        facd *= j;
        powx *= facx;
        sum += facn/facd*powx;
      }
      value *= sum;
      return value;
    }
  }

  private static double poly(double[] c, double x) {
    int i = c.length-1;
    double sum = c[i];
    for (--i; i>=0; --i) {
      sum *= x;
      sum += c[i];
    }
    return sum;
  }

  private static double sign(double x) {
    if (x>0.0) return 1.0;
    else if (x<0.0) return -1.0;
    else return 0.0;
  }
}
