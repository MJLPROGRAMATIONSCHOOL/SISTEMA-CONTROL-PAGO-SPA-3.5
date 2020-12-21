/* global Highcharts */

$(document).ready(function () {
   var dias = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'],
           meses = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto',
              'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

   function fnGenerarReporte1() {
      $.post("../reporte?accion=reporteSemanal", null, function (response) {
         var data1 = checkFecha(response.thisWeek, true);
         var data2 = checkFecha(response.lastWeek, true);
         showReportVentas(data1, data2);
      });
   };

   function fnGenerarReporte2() {
      $.post("../reporte", {accion: "reporteTotal"}, function (response) {
         console.log(response);
         $("#cat").text(response.total[0].total);
         $("#matri").text(response.total[1].total);
         $("#alum").text(response.total[2].total);
         $("#emp").text(response.total[3].total);
         // showReportPlatillos(response.lista);
      });
   };

   function fnGenerarReporte3() {
      $.post("../reporte", {accion: "reporteComprasVentas"}, function (response) {
         var data_compras = checkFecha(response.data_compras, false);
         var data_ventas = checkFecha(response.data_ventas, false);
         showReportComprasVentas(data_compras, data_ventas);
      });
   };

   function checkFecha(arreglo, campo) {
      var array_main = campo ? dias : meses,
              datos = [];
      for (var i = 0; i < array_main.length; i++) {
         var pos = -1;
         for (var j = 0; j < arreglo.length; j++) {
            if (arreglo[j].fecha === array_main[i]) {
               pos = j;
            }
         }
         datos.push(pos !== -1 ? parseFloat(arreglo[pos].total) : null);
      }
      return datos;
   };

   function showReportVentas(arr1, arr2) {
      Highcharts.chart('cont_sales', {
         chart: {
            type: 'line'
         },
         title: {
            text: 'Ventas Semanales'
         },
         xAxis: {
            categories: dias
         },
         yAxis: {
            title: {
               text: 'Monto S/.'
            }
         },
         plotOptions: {
            line: {
               dataLabels: {
                  enabled: true
               },
               enableMouseTracking: false
            }
         },
         series: [{
               name: 'Esta semana',
               data: arr1
            }, {
               name: 'Semana pasada',
               data: arr2
            }]
      });
   };

   function showReportPlatillos(list) {
      var array = [];
      for (var i = 0; i < list.length; i++) {
         var obj = {};
         obj.name = list[i].descripcion;
         obj.y = parseInt(list[i].total);
         if (i === 0) {
            obj.sliced = true;
            obj.selected = true;
         }
         array.push(obj);
      }

      Highcharts.chart('cont_plt', {
         chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
         },
         title: {
            text: 'Platillo más vendido, ' + list[0].fecha + ' 2019'
         },
         tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
         },
         plotOptions: {
            pie: {
               allowPointSelect: true,
               cursor: 'pointer',
               dataLabels: {
                  enabled: false
               },
               showInLegend: true
            }
         },
         series: [{
               name: 'Brands',
               colorByPoint: true,
               data: array
            }]
      });
   };

   function showReportComprasVentas(array_compras, array_ventas) {
      Highcharts.chart('cont_end', {
         chart: {
            type: 'column'
         },
         title: {
            text: 'Ingresos/Egresos mensuales'
         },
         xAxis: {
            categories: meses,
            crosshair: true
         },
         yAxis: {
            min: 0,
            title: {
               text: 'Total S/.'
            }
         },
         tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>S/. {point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
         },
         plotOptions: {
            column: {
               pointPadding: 0.2,
               borderWidth: 0
            }
         },
         series: [{
               name: 'Compras',
               data: array_compras

            }, {
               name: 'Ventas',
               data: array_ventas

            }]
      });
   };

   //fnGenerarReporte1();
   fnGenerarReporte2();
   //fnGenerarReporte3();
});
