/**
 * 
 */
package se.yoctocontainer.xcoder.decode;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import se.yoctocontainer.core.AbstractComponent;
import se.yoctocontainer.xcoder.Encodable;

/**
 * @author jpeter
 * @param <A>
 *
 */
public abstract class AbstractDecoder<A extends Encodable> extends AbstractComponent implements
		Decoder<A> {

	protected Reader reader;
	private DecoderTask task;
	protected Class<A> myClass;

	@SuppressWarnings("unchecked")
	@Override
	public A decodeNext() throws IOException {
		return (A) task.decode(fetchNext());
	}
	
	@Override
	public List<A> decodeAll() throws IOException {
		List<A> list = new ArrayList<A>();
		
		while(hasNext()) {
			list.add(decodeNext());
		}
		return list;
	}
	
	@Override
	public boolean hasNext() {
		if(reader == null) {
			return false;
		}
		try {
			return reader.ready();
		} catch (IOException e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public A waitAndDecodeNext() throws IOException {
		return (A) task.decode(waitAndFetchNext());
	}

	@Override
	protected void init() {
		
		
	}
	
	/**
	 * Get the next String to decode
	 * @return
	 * @throws IOException 
	 */
	protected abstract String fetchNext() throws IOException;
	
	/**
	 * Get the next String to decode, wait if needed
	 * @return
	 * @throws IOException 
	 */
	protected abstract String waitAndFetchNext() throws IOException;

	@Override
	public void setDecoderTask(DecoderTask task) {
		this.task = task;
	}

}
