package Transformers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import Transformers.model.Transformer;

@Component
public class DataLoader {

	@Autowired
	private TransformerRepository repository;
	
	public void loadDefaultData() {
		if(repository.count() == 0) {
			repository.save(new Transformer("Soundwave", "D", 8,9,2,9,8,5,9,10));
			repository.save(new Transformer( "Bluestreak", "A", 6,6,7,9,1,2,9,7));
			repository.save(new Transformer( "Hubcap", "A", 4,4,4,4,4,4,4,4));	
			repository.save(new Transformer("Arcee", "A", 8,9,2,6,7,5,6,10));
			repository.save(new Transformer( "Predaking", "D", 6,6,7,9,7,2,9,7));
			repository.save(new Transformer( "Optimus Prime", "A", 6,6,7,9,7,2,9,7));
			repository.save(new Transformer( "Megatron", "D", 4,4,4,4,4,7,5,9));	
		}
	}
}
