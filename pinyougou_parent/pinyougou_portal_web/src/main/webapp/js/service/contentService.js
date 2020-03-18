//服务层
app.service('contentService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../content/findAll.shtml');
	}
	//分页 
	this.findPage=function(page,size){
		return $http.get('../content/findPage.shtml?page='+page+'&size='+size);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../content/findOne.shtml?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../content/add.shtml',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../content/update.shtml',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../content/delete.shtml?ids='+ids);
	}
	//搜索
	this.search=function(page,size,searchEntity){
		return $http.post('../content/search.shtml?page='+page+"&size="+size, searchEntity);
	}

	//根据广告类型id查询列表
	this.findByCategoryId = function (categoryId) {
		return $http.get("../content/findByCategoryId.shtml?categoryId=" + categoryId);

	};
});
