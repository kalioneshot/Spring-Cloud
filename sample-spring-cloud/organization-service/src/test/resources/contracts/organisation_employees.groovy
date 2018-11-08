import org.springframework.cloud.contract.spec.Contract

/* 	
 * Contract tested
 * @GetMapping("/{id}/with-employees")
 * public Organization findByIdWithEmployees(@PathVariable("id") Long id 
 *
 */
Contract.make {
    name "should_provide_connections"
    request {
        method 'GET'
        url '/1/with-employees'
    }
    response {
        status 200
		headers {
      		contentType(applicationJson())
    	}
   		body(file("organization_employees_response.json"))
    }
}
