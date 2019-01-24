/*
 Table plugin for jQuery
 Copyright (c) 2016 Gabriel Rodrigues e Gabriel Leite (http://gabrielr47.github.io/)
 Licensed under the MIT license
 Version: 0.2
 */

$.fn.easyTable = function (options) {
   var trIndex = 'all';
   this.options = {
      tableStyle: 'table easyTable',
      hover: 'btn-success',
      buttons: true,
      select: true,
      sortable: true,
      scroll: {active: false, height: '400px'}
   };
   this.message = {
      all: 'Select All',
      clear: 'Clear',
      search: 'Search'
   };
   this.select = function () {
      var table = this;
      var options = this.options;
      var posCurrentTr = 0;
      var pressCrl = false;
      var pressShift = false;
      var pressDir = '';
      var posIniShift = 0;
      var maxLength = table.find('tbody tr').length - 1;
      table.find('tbody').on('click', 'tr', function () {
         if (pressCrl) {
            $(this).toggleClass(options.hover);
            posCurrentTr = $(this).index();
            posIniShift = posCurrentTr;
         } else if (pressShift) {
            $(this).toggleClass(options.hover);
         } else {
            table.find('tbody tr').removeClass(options.hover);
            $(this).addClass(options.hover);
            posCurrentTr = $(this).index();
            posIniShift = posCurrentTr;
         }
      });
   };
   this.sortable = function () {
      function sortTr(table, col, reverse) {
         var tb = table.tBodies[0];
         var tr = Array.prototype.slice.call(tb.rows, 0);
         var i;
         reverse = -((+reverse) || -1);
         var str1;
         var str2;
         tr = tr.sort(function (a, b) {

            if (a.cells[col].children[0] === undefined) {
               str1 = a.cells[col].textContent.trim();
               str2 = b.cells[col].textContent.trim();
            } else {
               str1 = a.cells[col].getElementsByTagName(a.cells[col].children[0].tagName)[0].value;
               str2 = b.cells[col].getElementsByTagName(a.cells[col].children[0].tagName)[0].value;
            }

            if (!isNaN(str1)) {
               if (str1.length === 1) {
                  str1 = '0' + str1;
               }
               if (str2.length === 1) {
                  str2 = '0' + str2;
               }
            }
            return reverse * (str1.localeCompare(str2));
         });

         for (i = 0; i < tr.length; ++i) {
            tb.appendChild(tr[i]);
         }
      }

      this.makeSortable = function (table) {
         var th = table.tHead;
         var tablePlugin = this;
         var i;
         th && (th = th.rows[0]) && (th = th.cells);

         if (th) {
            i = th.length;
         } else {
            return;
         }

         while (--i >= 1) {
            (function (i) {
               var dir = 1;
               $(th[i]).append(' <i class="fa fa-sort-amount-asc  hidden" data-order="up"></i>');
               $(th[i]).append(' <i class="fa fa-sort-amount-desc hidden" data-order="down"></i>');
               $(th[i]).click(function () {
                  trIndex = $(th[i]).index();
                  $("#search").attr('placeholder', tablePlugin.message.search + ' ' + $(th[i]).text());
                  sortTr(table, i, (dir = 1 - dir));
                  if ((1 - dir) === 1) {
                     $(th).find('i[data-order=down],i[data-order=up]').addClass('hidden');
                     $(th[i]).find('i[data-order=up]').removeClass('hidden');
                  } else {
                     $(th).find('i[data-order=down],i[data-order=up]').addClass('hidden');
                     $(th[i]).find('i[data-order=down]').removeClass('hidden');
                  }
               });
            }(i));
         }
      };

      this.makeAllSortable = function (table) {
         var t = table;
         var i = t.length;
         while (--i >= 0) {
            this.makeSortable(t[i]);
         }
      };

      this.makeAllSortable(this);

   };
   this.buttons = function () {
      var table = this;
      var menu = '<div id=\'easyMenuTable\' class=\'row\'><div class=\'col-md-6 pull-left\'></div> <div class=\'col-md-4 pull-right\'></div></div>';
      this.parent().prepend(menu);
    
   };
   this.filter = function () {
      var table = this;
      var menuId = $("#easyMenuTable .pull-right");
      var search = '<input type=\'text\' class=\'form-control\' placeholder=\'' + this.message.search + '\' id=\'search\'>';
      menuId.prepend(search);
      if (trIndex === 'all') {
         $("#search").attr('placeholder', $("#search").attr('placeholder') + ' Todos');
      } else {
      }
      $("#search").keyup(function () {
         var colunaSel = false;
         var termo = $(this).val().toLowerCase();
         table.find('tbody tr').each(function () {
            if (trIndex === 'all') {
               var td = $(this).find('td');
            } else {
               var td = $(this).find("td:eq(" + trIndex + ")");
            }
            if (td.text().toLowerCase().indexOf(termo) > -1) {
               colunaSel = true;
            }
            if ((!colunaSel)) {
               $(this).hide();
            } else
               $(this).show();
            colunaSel = false;

         });
      });
   };
   this.scroll = function () {
      this.find('tbody').css('height', this.options.scroll.height);
   };
   this.getSelected = function (col) {
      var selected = [];      
      this.find('tbody > tr').has( 'input[type=checkbox]:checked').each(function (key, val) {
    	  var obj = new Object;
    	  obj.empNo = $(val).find('td').eq(1).text();
    	  obj.name = $(val).find('td').eq(2).text();
    	  obj.email = $(val).find('td').eq(3).text();
    	  selected = selected.concat(obj);
      });
      
      return JSON.stringify(selected);
   };
   this.create = function () {
      $("#easyMenuTable").remove();
      this.options = $.extend({}, this.options, options);
      this.addClass(this.options.tableStyle);
      this.attr('tabindex', 0);

      if (this.options.select) {
         this.select();
      }
      if (this.options.sortable) {
         this.sortable();
      }
      if (this.options.buttons) {
         this.buttons();
         this.filter();
      }
      if (this.options.scroll.active) {
         this.scroll();
      }
   };
   this.create();
   
   $('#checkAll').change(function() {
	  var checkboxes = table.find('input[type=checkbox]:checkbox');
	  if($(this).is(':checked')) {
		  checkboxes.prop("checked", true);
	  } else {
		  checkboxes.prop("checked", false);
	  }

   });
   
   return this;
};

