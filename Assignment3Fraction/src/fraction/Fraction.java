package fraction;
/**
 * Jonathyn Komorita
 * CSCD 350
 * Fraction class that handles basic Fraction functionality
 */
public class Fraction implements Comparable<Fraction> {

	private int num;
	private int denom;
	
	public Fraction( int num, int denom ) {
		int[] checkedInputs = checkInputs( num, denom );
		this.num = checkedInputs[0];
		this.denom = checkedInputs[1];
	}
	
	private int[] checkInputs( int num, int denom ) {
		int[] checkedInputs = checkNegatives( num, denom );
		checkedInputs = checkReduction( checkedInputs[0] , checkedInputs[1] );
		
		return checkedInputs;
	}
	
	private int[] checkNegatives(int num, int denom) {
		if( num < 0 && denom < 0 ) {
			num = -num;
			denom = -denom;
		}
		else if( num >= 1 && denom < 0 ) {
			num = -num;
			denom = -denom;
		}
		else if( denom == 0 ) {
			throw new IllegalArgumentException("The denominator with the value of 0 is not permitted");
		}
		return new int[] {num,denom};
	}
	
	private int[] checkReduction( int num, int denom ) {
		int gcd = gcd( num, denom );
		if( gcd == 1 || gcd == -1 )
			return new int[] {num,denom};
		return new int[] { num/gcd, denom/gcd };
	}

	private int gcd( int num, int denom ) {
		if( denom == 0 )
			return num;
		return gcd( denom, num%denom );
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getDen() {
		return denom;
	}

	public void setDen(int denom) {
		this.denom = denom;
	}
	
	public boolean equals(Fraction f) {
		int[] arr = checkReduction( f.getNum(),f.getDen() );
		if( this.num == arr[0] && this.denom == arr[1] )
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(Fraction f) {
		double thisFraction = Double.valueOf(this.num) / Double.valueOf(this.denom);
		double compFraction = Double.valueOf(f.getNum()) / Double.valueOf(f.getDen());
		int returnValue = Integer.MAX_VALUE;
		if( thisFraction > compFraction )
			returnValue = 2;
		else if( thisFraction < compFraction ) 
			returnValue = -1;
		else
			returnValue = 0;
		return returnValue;
	}
	
	@Override
	public String toString() {
		return this.num + "/" + this.denom;
	}
	
	public Fraction add( Fraction f ) {
		int sumNum = 0;
		int productDenom = 0;
		if( f == null ) {
			throw new NullPointerException("Cannot perform math operations on a null fraction object!");
		}
		else if( this.denom == f.denom ) {
			sumNum = this.num + f.num;
			productDenom = this.denom;
		}
		else {
			productDenom = this.denom * f.denom;
			sumNum = ( this.num * f.denom ) + ( this.denom * f.num );
		}
		int[] reduced = checkReduction( sumNum, productDenom );
		return new Fraction( reduced[0] , reduced[1] );
	}
	
	public Fraction multiply( Fraction f ) {
		if( f == null ) {
			throw new NullPointerException("Cannot perform math operations on a null fraction object!");
		}
		int[] reduced = checkReduction( this.num * f.num , this.denom * f.denom );
		return new Fraction( reduced[0] , reduced[1] );
	}
	
	public double realValue() {
		return Double.valueOf(this.num) / Double.valueOf(this.denom);
	}
	
	
	
}
