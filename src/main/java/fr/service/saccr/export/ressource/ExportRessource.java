package fr.service.saccr.export.ressource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import fr.service.saccr.export.service.ExportServiceZDE;
import fr.service.saccr.export.service.ExportServiceZDP;
import fr.service.saccr.utils.ParamJSON;
import fr.service.saccr.utils.ResponseJSON;
import javax.validation.Valid;

@Validated
@RestController("/Service")

public class ExportRessource {
    @Autowired
	private ExportServiceZDE exportServiceZDE;
    @Autowired
	private ExportServiceZDP exportServiceZDP;


	@PostMapping("Service/API/Export/ZDE/")
	@ResponseBody
	public ResponseJSON exportZDEService(@Valid @RequestBody ParamJSON paramJSON) 
			throws Exception	    {
		       return exportServiceZDE.processZDE(paramJSON.getQuery(),paramJSON.getFilePath(),paramJSON.getFileName(),paramJSON.getParams());
		    }
	@PostMapping("Service/API/Export/ZDP/")
	@ResponseBody
	public ResponseJSON exportZDPService(@Valid @RequestBody ParamJSON paramJSON)
		    throws Exception
		    {
		       return exportServiceZDP.processZDP(paramJSON.getQuery(),paramJSON.getFilePath(),paramJSON.getFileName(),paramJSON.getParams());
		    }
}