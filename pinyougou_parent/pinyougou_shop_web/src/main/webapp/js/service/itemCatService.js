//服务层
app.service('itemCatService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('/itemCat/findAll.shtml');
	}
	//分页 
	this.findPage=function(page,size){
		return $http.get('/itemCat/findPage.shtml?page='+page+'&size='+size);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('/itemCat/findOne.shtml?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('/itemCat/add.shtml',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('/itemCat/update.shtml',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('/itemCat/delete.shtml?ids='+ids);
	}
	//搜索
	this.search=function(page,size,searchEntity){
		return $http.post('/itemCat/search.shtml?page='+page+"&size="+size, searchEntity);
	}

	//根据父id查询分类列表
	this.findByParentId=function(parentId){
		return $http.post('/itemCat/findByParentId.shtml?parentId='+parentId);
	}
});
