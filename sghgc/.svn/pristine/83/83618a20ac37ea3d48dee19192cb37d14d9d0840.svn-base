/*大屏*/
var geoCoordMap = {
    '北门': [119.74617004394531,40.01213479170217],
    '钟鼓楼': [119.75020408630371,40.00733982613045],
    '东门': [119.75449562072752,40.00939015682881],
    '南门': [119.75475311279297,40.00143502275777],
    '西门': [119.74464654922485,40.00510041663019]
};

var DMData = [
    [{
        name: '东门'
    }, {
        name: '东门',
        value: 200
    }],
    [{
        name: '东门'
    }, {
        name: '钟鼓楼',
        value: 90
    }]
];

var ZGLData = [
    [{
        name: '钟鼓楼'
    }, {
        name: '钟鼓楼',
        value: 200
    }],

    [{
        name: '钟鼓楼'
    }, {
        name: '南门',
        value: 150
    }],
    [{
        name: '钟鼓楼'
    }, {
        name: '北门',
        value: 100
    }],
	[{
        name: '钟鼓楼'
    }, {
        name: '东门',
        value: 250
    }],
	[{
        name: '钟鼓楼'
    }, {
        name: '西门',
        value: 60
    }]
];

var BMLData = [
    [{
        name: '北门'
    }, {
        name: '钟鼓楼',
        value: 80
    }],

];

var planePath = 'path://M.6,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705';

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var dataItem = data[i];
        var fromCoord = geoCoordMap[dataItem[0].name];
        var toCoord = geoCoordMap[dataItem[1].name];
        if (fromCoord && toCoord) {
            res.push([{
                coord: fromCoord
            }, {
                coord: toCoord
            }]);
        }
    }
    return res;
};

var color = ['#3ed4ff', '#ffa022', '#a6c84c'];
var series = [];
[
    ['东门', DMData],
    ['钟鼓楼', ZGLData],
    ['北门', BMLData]
].forEach(function (item, i) {
    series.push({
        name: item[0],
        type: 'lines',
        zlevel: 1,
        effect: {
            show: true,
            period: 6,
            trailLength: 0.7,
            color: '#fff',
            symbolSize: 3
        },
        lineStyle: {
            normal: {
                color: color[i],
                width: 0,
                curveness: 0.01 //0.2
            }
        },
        data: convertData(item[1])
    }, {
        name: item[0],
        type: 'lines',
        zlevel: 2,
        effect: {
            show: true,
            period: 6,
            trailLength: 0,
            symbol: planePath,
            symbolSize: 15
        },
        lineStyle: {
            normal: {
                color: color[i],
                width: 1,
                opacity: 0.4,
                curveness: 0.01 //0.2
            }
        },
        data: convertData(item[1])
    }, {
        name: item[0],
        type: 'effectScatter',
        coordinateSystem: 'geo',
        zlevel: 2,
        rippleEffect: {
            brushType: 'stroke'
        },
        label: {
            normal: {
                show: false,
                position: 'right',
                formatter: '{b}'
            }
        },
        symbolSize: function (val) {
            return val[2] / 8;
        },
        itemStyle: {
            normal: {
                color: color[i]
            }
        },
        data: item[1].map(function (dataItem) {
            return {
                name: dataItem[1].name,
                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
            };
        })
    });
});

option = {
	
    backgroundColor: '#080a20',
    title: {
        left: 'left',
        textStyle: {
            color: '#fff'
        }
    },
	
    legend: {
        orient: 'vertical',
        top: 'bottom',
        left: 'right',
        data: [' ', '  ', '   '],
        textStyle: {
            color: '#fff'
        },
        selectedMode: 'single'
    },
	tooltip: {
		show:true,
		trigger: 'item',
		alwaysShowContent:false,
		backgroundColor: '#0C121C',
		borderColor: 'rgba(0,0,0,0.16);',
		hideDelay:100,
		triggerOn:'mousemove',
		enterable:true,
		textStyle:{
			color:'#DADADA',
			fontSize:'12',
			width:20,
			height:30,
			overflow:'break'
		},
		formatter(params){
			return `古城：${params.data.name}`;
		},
		showDelay:100
	},
    geo: {
		name: '山海关古城片区分布图',
        map: 'shggc',
        zoom: 1,
        label: {
            show: true,
            color: '#BBC0CA',
        },
        emphasis: {   //鼠标浮动的时候，标注和各个地图区域的交互渲染设置
            label: {
                show: true,
                // fontSize: 16,
                color: '#39E639',
            },
            itemStyle: {
				//areaColor: '#0b1c2d'
				normal: {
					color: '#ffeb7b'
				}
            },
					
        },
        roam: false,//禁止拖拽，缩放
		/*
        itemStyle: {
            normal: {
                areaColor: '#142957',
                borderColor: '#0692a4'
            },
            emphasis: {
                areaColor: '#0b1c2d'
            }
        }
		*/
		itemStyle: {
				normal: {
					areaColor: 'rgba(2,37,101,.5)',
					borderColor: 'rgba(112,187,252,.5)'
				},
				emphasis: {
					areaColor: 'rgba(2,37,101,.8)'
				}
		},
		// 文本位置修正
        textFixed: {
            Alaska: [20, -20],
        }
		
    },
    series: series,
};
var myecharts = echarts.init($('.map .geo')[0])
myecharts.setOption(option)


//drawHeat();

//渲染热力图
function drawHeat(){
	/*
	var data = [[{"coord": [119.74464654922485,40.00510041663019],"elevation": 81},{"coord": [119.75475311279297,40.00143502275777],"elevation": 215}],[{"coord": [119.74464654922485,40.00510041663019],"elevation": 11},{"coord": [119.75020408630371,40.00733982613045],"elevation": 115}]];

	const points = [].concat.apply([], data.map(function (track) {
		return track.map(function (seg) {
			return seg.coord.concat([1]);
		});
	}));
	//const myChart = echarts.getInstanceByDom(this.$('.map .geo')[0].map);
	*/
	const myChart = echarts.init($('.map .geo')[0]);
	var canvas = myChart.getRenderedCanvas({
		pixelRatio: 2,
		backgroundColor: '#fff'
	});
	var context = canvas.getContext("2d");
	var pointsdata = [
        {
          x: 100,
          y: 100,
          value: 80,
        },
        {
          x: 140,
          y: 140,
          value: 50,
        },
        {
          x: 471,
          y: 277,
          value: 25,
        },
        {
          x: 438,
          y: 375,
          value: 97,
        },
        {
          x: 373,
          y: 19,
          value: 71,
        },
        {
          x: 473,
          y: 42,
          value: 63,
        },
        {
          x: 463,
          y: 95,
          value: 97,
        },
        {
          x: 590,
          y: 437,
          value: 34,
        },
        {
          x: 377,
          y: 442,
          value: 66,
        },
        {
          x: 171,
          y: 254,
          value: 20,
        },
        {
          x: 6,
          y: 582,
          value: 64,
        },
        {
          x: 387,
          y: 477,
          value: 14,
        },
        {
          x: 300,
          y: 300,
          value: 80,
        },
      ];
      
	  /*
	  var canvas = document.getElementById("hotmap");
        if (canvas.getContext) {
          var context = canvas.getContext("2d");
        } else {
          alert("浏览器不支持canvas!");
        }
        context.clearRect(0, 0, 800, 800);
		*/
        /*
         * radius: 绘制半径，请自行设置
         * min, max: 强弱阈值，可自行设置，也可取数据最小最大值
         */
        var radius = 50;
        var max = 97;
        var min = 14;
        pointsdata.forEach((point) => {
          let { x, y, value } = point;
          context.beginPath();
          context.arc(x, y, radius, 0, 2 * Math.PI);
          context.closePath();
 
          // 创建渐变色: r,g,b取值比较自由，我们只关注alpha的数值
          let radialGradient = context.createRadialGradient(
            x,
            y,
            0,
            x,
            y,
            radius
          );
          radialGradient.addColorStop(0.0, "rgba(0,0,0,1)");
          radialGradient.addColorStop(1.0, "rgba(0,0,0,0)");
          context.fillStyle = radialGradient;
 
          // 设置globalAlpha: 需注意取值需规范在0-1之间
          let globalAlpha = (value - min) / (max - min);
          context.globalAlpha = Math.max(Math.min(globalAlpha, 1), 0);
 
          // 填充颜色
          context.fill();
        });
        const defaultColorStops = {
          0: "#0ff",
          0.2: "#0f0",
          0.4: "#ff0",
          1: "#f00",
        };
        const width = 20,
          height = 256;
 
        function Palette(opts) {
          Object.assign(this, opts);
          this.init();
        }
 
        Palette.prototype.init = function() {
          let colorStops = this.colorStops || defaultColorStops;
 
          // 创建canvas
          let canvas = document.createElement("canvas");
          canvas.width = width;
          canvas.height = height;
          let ctx = canvas.getContext("2d");
 
          // 创建线性渐变色
          let linearGradient = ctx.createLinearGradient(0, 0, 0, height);
          for (const key in colorStops) {
            linearGradient.addColorStop(key, colorStops[key]);
          }
 
          // 绘制渐变色条
          ctx.fillStyle = linearGradient;
          ctx.fillRect(0, 0, width, height);
 
          // 读取像素数据
          this.imageData = ctx.getImageData(0, 0, 1, height).data;
          this.canvas = canvas;
        };
 
        /**
         * 取色器
         * @param {Number} position 像素位置
         * @return {Array.<Number>} [r, g, b]
         */
        Palette.prototype.colorPicker = function(position) {
          return this.imageData.slice(position * 4, position * 4 + 3);
        };
        console.log(Date.now());
        // 像素着色
        let imageData = context.getImageData(0, 0, 800, 800);
        let data = imageData.data;
        let palette = new Palette(context);
        for (var i = 3; i < data.length; i += 4) {
          let alpha = data[i];
          let color = palette.colorPicker(alpha);
          data[i - 3] = color[0];
          data[i - 2] = color[1];
          data[i - 1] = color[2];
        }
        context.putImageData(imageData, 0, 0);
		console.log(Date.now());
	
	/*
	const option = {
		animation: false,
		visualMap: {
			show: false,
			top: 'top',
			min: 0,
			max: 5,
			seriesIndex: 0,
			calculable: true,
			inRange: {
				color: ['blue', 'blue', 'green', 'yellow', 'red']
			}
		},
		series: [{
			type: 'heatmap',
			coordinateSystem: 'bmap',
			data: points,
			pointSize: 5,
			blurSize: 6,
			gradientColors: [{
				offset: 0.4,
				color: 'green'
			}, {
				offset: 0.5,
				color: 'yellow'
			}, {
				offset: 0.8,
				color: 'orange'
			}, {
				offset: 1,
				color: 'red'
			}]
		}]
	}
	myChart.setOption(option)
	*/
}