(function(jsGrid, $, undefined) {
	
    var Field = jsGrid.Field;
    
    function TextField(config) {
        Field.call(this, config);
    }

    TextField.prototype = new Field({    	
        autosearch: true,
		readOnly: false,

		filterTemplate: function() {
            if(!this.filtering)
                return "";

            var grid = this._grid,
                $result = this.filterControl = this._createTextBox();
            
            if(this.autosearch) {
                $result.on("keypress", function(e) {
                    if(e.which === 13) {
                        grid.search();
                        e.preventDefault();
                    }
                });
            }

            return $result;
        },

        insertTemplate: function() {
            if(!this.inserting)
                return "";

            return this.insertControl = this._createTextBox();
        },

        editTemplate: function(value) {
        	
            if(!this.editing)
                return this.itemTemplate.apply(this, arguments);

            var $result = this.editControl = this._createTextBox();
            $result.val(value);
            
            $.getScript("/js/pickdatetime.js");
            
            return $result;
        },

        filterValue: function() {
            return this.filterControl.val();
        },

        insertValue: function() {
            return this.insertControl.val();
        },

        editValue: function() {
            return this.editControl.val();
        },

        _createTextBox: function() {
        	var editName = "txt" + this.name;        	
            return $("<input>").attr("type", "text").attr("name", editName)
                .prop("readonly", !!this.readOnly);
        }
    });
    
    jsGrid.fields.text = jsGrid.TextField = TextField;

}(jsGrid, jQuery));
