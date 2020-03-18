//控制层
app.controller('goodsController', function ($scope, $controller,$location, goodsService, uploadService,itemCatService,typeTemplateService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        goodsService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, list) {
        goodsService.findPage(page, list).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function () {

        var id= $location.search()['id'];//获取参数值
        if(id==null){
            return ;
        }

        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;

                //向富文本编辑器添加商品介绍
                editor.html($scope.entity.goodsDesc.introduction);

                //显示图片列表
                $scope.entity.goodsDesc.itemImages = JSON.parse($scope.entity.goodsDesc.itemImages);

                //显示扩展属性
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.entity.goodsDesc.customAttributeItems);

                //规格
                $scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems);

                //SKU列表规格列转换
                for( var i=0;i<$scope.entity.itemList.length;i++ ){
                    $scope.entity.itemList[i].spec =
                        JSON.parse( $scope.entity.itemList[i].spec);
                }
            }
        );
    }

    //根据规格名称和规格和规格选项返回是否被勾选
    $scope.checkAttributeValue = function (specName, optionName) {
        var items = $scope.entity.goodsDesc.specificationItems;
        var object = $scope.searchObjectByKey(items, 'attributeName', specName);
        if (object == null) {
            return false;
        } else {
            if (object.attributeValue.indexOf(optionName) >= 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    //保存
    $scope.save = function () {

        //富文本框内容提取并赋值给商品描述
        $scope.entity.goodsDesc.introduction=editor.html();

        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = goodsService.update($scope.entity); //修改
        } else {
            serviceObject = goodsService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                alert(response.message);
                if (response.success) {
                    alert('保存成功');
                    //重新查询
                    // $scope.reloadList();//重新加载
                    //清空数据
					$scope.entity = {};
                    editor.html('');//清空富文本编辑器
                }else{
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, size) {
        goodsService.search(page, size, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    /**
     * 上传图片
     */
    $scope.uploadFile=function(){
        uploadService.uploadFile().success(function(response) {
            if(response.success){//如果上传成功，取出url
                $scope.image_entity.url=response.message;//设置文件地址
            }else{
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });
    };

    $scope.entity={goods:{},goodsDesc:{itemImages:[]}};//定义页面实体结构
    //添加图片列表
    $scope.add_image_entity=function(){
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }
    //列表中移除图片
    $scope.remove_image_entity=function (index) {
        $scope.entity.goodsDesc.itemImages.splice(index, 1);
    }


    //读取一级分类
    $scope.selectItemCat1List=function () {
        itemCatService.findByParentId(0).success(
            function (response) {
                $scope.itemCat1List = response;
            }
        )
    }
    //读取二级分类
    $scope.$watch('entity.category1Id',function (newValue, oldValue) {
        //根据选择的值，查询二级分类
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCat2List = response;
            }
        )
    })
    //读取三级分类
    $scope.$watch('entity.category2Id',function (newValue, oldValue) {
        //根据选择的值，查询三级分类
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCat3List = response;
            }
        )
    })
    //三级分类选择后，读取模板id
    $scope.$watch('entity.category3Id',function (newValue, oldValue) {
        itemCatService.findOne(newValue).success(
            function (response) {
                $scope.entity.typeTemplateId=response.typeId;//更新模板ID
            }
        )

    })


    //模板ID选择后  更新品牌列表
    $scope.$watch('entity.typeTemplateId', function(newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
            function(response){
                //获取类型模板
                $scope.typeTemplate=response;

                //品牌列表
                $scope.typeTemplate.brandIds= JSON.parse( $scope.typeTemplate.brandIds);

                //如果没有传递id，则加载模板中的扩展数据，如果有则加载由id查询的数据
                if ($location.search()['id'] == null) {
                    //扩展属性
                    $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
                }

            }
        );

        //规格列表
        typeTemplateService.findSpecList(newValue).success(
            function (response) {
                $scope.specList = response;
            }
        )

    });

    //保存规格选项，格式[{“attributeName”:”规格名称”,”attributeValue”:[“规格选项1”,“规格选项2”.... ]  } , ....  ]
    $scope.entity={ goodsDesc:{itemImages:[],specificationItems:[]}  };

    $scope.updateSpecAttribute=function($event,name,value){
        var object= $scope.searchObjectByKey(
            $scope.entity.goodsDesc.specificationItems ,'attributeName', name);
        if(object!=null){
            if($event.target.checked ){
                object.attributeValue.push(value);
            }else{//取消勾选
                object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
                //如果选项都取消了，将此条记录移除
                if(object.attributeValue.length==0){
                    $scope.entity.goodsDesc.specificationItems.splice(
                        $scope.entity.goodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else{
            $scope.entity.goodsDesc.specificationItems.push(
                {"attributeName":name,"attributeValue":[value]});
        }
    }


    //创建sku列表
    $scope.createItemList=function () {
        //初始
        $scope.entity.itemList=[{spec:{},price:0,num:9999,status:'0', isDefault: '0'}]

        var items = $scope.entity.goodsDesc.specificationItems;
        for (var i = 0; i < items.length; i++) {
            $scope.entity.itemList = addColumn($scope.entity.itemList, items[i].attributeName, items[i].attributeValue);
        }
    }

    addColumn = function (list, columnName, conlumnValues) {
        var newList = [];//新集合
        for (var i = 0; i < list.length; i++) {
            var oldRow = list[i];
            for (var j = 0; j < conlumnValues.length; j++) {
                var newRow = JSON.parse(JSON.stringify(oldRow));//深克隆
                newRow.spec[columnName] = conlumnValues[j];
                newList.push(newRow);
            }
        }
        return newList;

    };


    //显示商家列表商品的状态
    $scope.status = ['未审核', '申请中', '审核通过', '已驳回'];//商品的状态

    //显示商品分类
    $scope.itemCatList = [];//商品分类列表
    //加载商品分类列表
    $scope.findItemCatList=function () {
        itemCatService.findAll().success(function (response) {
            for (var i = 0; i < response.length; i++) {
                $scope.itemCatList[response[i].id] = response[i].name;
            }
        })
    }



});
