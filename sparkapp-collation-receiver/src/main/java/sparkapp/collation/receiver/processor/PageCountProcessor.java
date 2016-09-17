package sparkapp.collation.receiver.processor;

import common.query.QueryParam;
import service.pagecount.PageCountService;
import service.pagecount.model.PageCountModel;
import sparkapp.collation.receiver.object.PartialPageCount;
import sparkapp.collation.receiver.service.impl.PartialPageCountService;

public class PageCountProcessor {

	private PartialPageCountService partialPageCountService;
	private PageCountService pageCountService;
	
	public PageCountProcessor(PartialPageCountService partialPageCountService, PageCountService pageCountService) {
		// TODO Auto-generated constructor stub
		this.partialPageCountService = partialPageCountService;
		this.pageCountService = pageCountService;
	}
	
	public void process(String url, String visitorID) throws Exception{
		for(PartialPageCount partialPageCount: partialPageCountService.getAll(url)){
			
			QueryParam<PageCountModel> param = new QueryParam<>(PageCountModel.class);
			param.getModel().setVISITORID(visitorID);
			param.getModel().setTKEY(partialPageCount.getTKEY());
			param.getModel().setMETRIC(partialPageCount.getMETRIC());
			param.getModel().setURL(partialPageCount.getURL());
			param.getModel().setTVALUES(partialPageCount.getTVALUES());
			PageCountModel pageCountModel = pageCountService.get(param);
			if(pageCountModel != null){
				pageCountModel.setTCOUNT(pageCountModel.getTCOUNT()+1);
				pageCountService.save(pageCountModel);
			} else {
				param.getModel().setTCOUNT(1L);
				pageCountService.save(param.getModel());
			}
			PageCountModel highest= pageCountService.getHighest(visitorID, param.getModel().getMETRIC());
		}
	}
}