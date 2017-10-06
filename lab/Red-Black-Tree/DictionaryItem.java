import java.util.Comparator;

public class DictionaryItem implements Comparable<DictionaryItem>{

	private String key;
	private String value;
	
	public DictionaryItem(String k, String v)
	{
		this.key=k;
		this.value=v;
	}

	@Override
	public int compareTo(DictionaryItem arg0) {
		// TODO Auto-generated method stub
		return this.key.compareTo(arg0.key);
	}

	public String toString() {
		return ""+this.key+": "+this.value;
	}

}
