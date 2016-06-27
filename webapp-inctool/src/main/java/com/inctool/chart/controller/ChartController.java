package com.inctool.chart.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inctool.chart.form.SeriesForm;
import com.inctool.chart.service.ChartService;
import com.inctool.common.form.ResponseForm;

@Controller
@RequestMapping("api/chart")
public class ChartController {

	@Autowired
	private ChartService chartService;
	
	
	@RequestMapping(value = "getMonthyCandidatesOfCompletion", method = RequestMethod.POST)
    public @ResponseBody ResponseForm<SeriesForm> getMonthyCandidatesOfCompletion(@RequestParam("id") String type) throws ParseException {
       
        ResponseForm<SeriesForm> response = new ResponseForm<SeriesForm>();
        response.setData(chartService.getMonthlyCountOfCompletion(type, ""));
        response.setMessage("SUCCESS");
        response.setStatus(true);
        return response;
    }
	
}
