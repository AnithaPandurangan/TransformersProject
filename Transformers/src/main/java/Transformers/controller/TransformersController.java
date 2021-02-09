package Transformers.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Transformers.model.*;
import Transformers.service.*;


@RestController
public class TransformersController {

	@Autowired
	private TranformerService transformerService;
	
  
	@RequestMapping( 
	        path = "/Transformers/", 
	        produces = "application/json",
	        method = RequestMethod.GET) 

	public List<Transformer> getTransformers() {
		List<Transformer> TransformersList = new ArrayList<Transformer>();
		
		TransformersList = transformerService.getAllTransformers();
		return TransformersList;
		
	}
   

 @RequestMapping(value = "/Transformers/", method = RequestMethod.POST)
   public List<Transformer> addTranformer( 
	        @RequestBody Transformer transformer) 
	    { 
	        transformerService.addTransformer(transformer); 
	        return getTransformers();
	    } 
	


 @RequestMapping(value = "/Transformers/{id}", method = RequestMethod.DELETE)
	public List<Transformer> deleteTransformer(@PathVariable("id") Long id) {
		Transformer transformer = transformerService.getById(id);
		if (transformer != null) {
			transformerService.deleteTransformer(id);
		}
		return getTransformers();
	}


 	@RequestMapping(value = "/Transformers/", method = RequestMethod.PUT)
	public List<Transformer> updateEmployee(@RequestBody Transformer transformer) {
	   Transformer transformerObj = transformerService.getById(transformer.getId());
		if (transformerObj != null) {
			 transformerService.addTransformer(transformer); 
		}
		return getTransformers();
	}

 	@RequestMapping(value="/Transformers/FindWinningTeam/", consumes="application/json")  
 	@ResponseBody
 	public String findWinningTeam(@RequestBody Map<String, List<Long>> transformerIds) {
 		long[] tansformerIdList = transformerIds.get("TransformerIds").stream().mapToLong(l -> l).toArray();
 		 return transformerService.findWinningTeam(tansformerIdList);
 	}
 
}
