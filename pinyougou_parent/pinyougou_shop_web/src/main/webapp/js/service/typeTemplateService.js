//服务层
app.service('typeTemplateService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('/typeTemplate/findAll.shtml');
	}
	//分页 
	this.findPage=function(page,size){
		return $http.get('/typeTemplate/findPage.shtml?page='+page+'&size='+size);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('/typeTemplate/findOne.shtml?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('/typeTemplate/add.shtml',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('/typeTemplate/update.shtml',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('/typeTemplate/delete.shtml?ids='+ids);
	}
	//搜索
	this.search=function(page,size,searchEntity){
		return $http.post('/typeTemplate/search.shtml?page='+page+"&size="+size, searchEntity);
	}

	//根据规格id查找规格选项列表
	this.findSpecList=function (id) {
		return $http.get("/typeTemplate/findSpecList.shtml?id=" + id);
	}
});
