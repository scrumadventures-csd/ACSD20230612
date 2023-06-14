const defaults = {
    responsive: true,
    pagination: true,
    search: true
};

const utils = {
    throttle: (callback, limit = 300) => {
        let lastFunc
        let lastRan
        return function() {
            // @ts-ignore
            const context = this;
            const args = arguments
            if (!lastRan) {
                callback.apply(context, args)
                lastRan = Date.now()
            } else {
                clearTimeout(lastFunc)
                lastFunc = setTimeout(function() {
                    if ((Date.now() - lastRan) >= limit) {
                        callback.apply(context, args)
                        lastRan = Date.now()
                    }
                }, limit - (Date.now() - lastRan))
            }
        }
    },
    debounce: (callback, timeout = 300) => {
        let timer;
        return (...args) => {
            clearTimeout(timer);
            timer = setTimeout(() => { callback.apply(this, args); }, timeout);
        };
    },
    sortJsonByIndex: ({
        array,
        dir,
        key
    }) => {
        return array.sort(function(a, b)
        {
            const x = a[key];
            const y = b[key];
            return (dir === 'desc' ? (x > y) ? -1 : ((x < y) ? 1 : 0) : (x < y) ? -1 : ((x > y) ? 1 : 0) );
        });
    },
    arraysEqual: (a, b) => {
        if (a === b) return true;
        if (a == null || b == null) return false;
        if (a.length !== b.length) return false;

        // If you don't care about the order of the elements inside
        // the array, you should sort both arrays here.
        // Please note that calling sort on an array will modify that array.
        // you might want to clone your array first.

        for (var i = 0; i < a.length; ++i) {
            if (a[i] !== b[i]) return false;
        }
        return true;
    },
    mergeNestedDefaults: (obj, objDefaults, arr) => {
        const mergedObj = {...objDefaults, ...obj};
        mergedObj.responsive = {...objDefaults.responsive, ...obj.responsive};
        arr.push(structuredClone(mergedObj));
    }
}

const _responsive = {
    handleResponsive: function ({
        table,
        tableContainer,
        tableHeader,
        tableBody,
        columns,
        setColumns,
        tableData,
        setTableData
    }) {
        const _this = this;
        const responsiveColumns = _this.handleResize({
            table,
            tableContainer,
            tableHeader,
            tableBody,
            columns,
        });
        const origHiddenColumns = [];
        const newHiddenColumns = [];
        columns.map((column) => {
            origHiddenColumns.push(column.hidden);
        })
        responsiveColumns.map((column) => {
            newHiddenColumns.push(column.hidden);
        })
        if(!utils.arraysEqual(origHiddenColumns,newHiddenColumns)){
            setColumns([...responsiveColumns]);
            tableData.forEach((row) => {
                row.responsive.open = false;
                row.responsive.details = _this.handleDetailsObj({
                    data: row,
                    columns: responsiveColumns
                });
            });
            setTableData([...tableData]);
        }
    },
    handleColumnsVisibility: ({
        columns,
        tableContainer
    }) => {
        let display = columns.map((column,index) => {
            return ['all','control'].indexOf(column.responsive.type) > -1;
        });

        let requiredWidth = 0;
        columns.forEach((column,index) => {
            if(column.visible && display[index]){
                requiredWidth += column.minWidth;
            }
        });
        const order = columns
            .map((column, index) => {
                return {
                    columnIndex: index,
                    responsivePriority: column.responsive.priority,
                    display: display[index]
                };
            })
            .sort( function ( a, b ) {
                if ( a.responsivePriority !== b.responsivePriority ) {
                    return a.responsivePriority - b.responsivePriority;
                }
                return a.columnIndex - b.columnIndex;
            });

        if(order.filter((item) => item.display).length === 1) order[1].display = true;
        const widthAvailable = tableContainer.offsetWidth;
        let usedWidth = widthAvailable - requiredWidth;
        let empty = false;
        order.forEach((column, index) => {
            const columnIndex = column.columnIndex;
            if(columns[columnIndex].minWidth){
                if(!column.display && (empty || usedWidth - columns[columnIndex].minWidth < 0)) {
                    empty = true;
                    columns[columnIndex].hidden = true;
                }
                else {
                    columns[columnIndex].hidden = false;
                }
            }

            usedWidth -= columns[columnIndex].minWidth;
        })
        return columns;
    },
    handleControl: ({
        columns
    }) => {
        const hiddenColumns = columns.filter((column) => column.hidden);
        const hasControl = columns.filter((column) => column.responsive.type === 'control');
        if(hasControl.length === 0)
        {
            columns.unshift({
                visible: hiddenColumns.length > 0,
                hidden: false,
                sortable: false,
                width: 14,
                minWidth: 14,
                responsive: {
                    type: "control",
                    priority: 1
                },
                name: ""
            });
        }
        else if(hiddenColumns.length === 0  && hasControl.length > 0)
        {
            columns[0].visible = false;
        }
        else if(hiddenColumns.length > 0  && hasControl.length > 0)
        {
            columns[0].visible = true;
        }
        return columns;
    },
    handleDetailsDisplay: function ({
        rowIndex,
        data,
        rowData,
        columns,
        open,
        setTableData
    }) {
        const detailsObj = this.handleDetailsObj({
            data: rowData,
            columns
        });

        if(open !== undefined)
        {
            const newDataRow = data[rowIndex];
            newDataRow.responsive.open = open;
            newDataRow.responsive.details = detailsObj;
            setTableData([...data]);
        }
    },
    handleExpandAll: function ({
        show,
        data,
        columns,
        setTableData,
        setExpandAll
    }) {
        const newData = [...data];
        const _this = this;
        newData.map((item) => {
            const detailsObj = _this.handleDetailsObj({
                data: item,
                columns
            });

            item.responsive.open = show;
            item.responsive.details = detailsObj;
        })
        setExpandAll(show);
        setTableData([...newData]);
    },
    handleDetailsObj: ({
        data,
        columns
    }) => {
        const details = {};
        columns.map((column) => {
            if(column.responsive.type !== 'control' && column.visible && column.hidden)
            {
                details[column.data] = data[column.name];
            }
        })
        return details;
    },
    handleResize: function ({
        table,
        tableContainer,
        tableHeader,
        tableBody,
        columns
    }) {
        const resizeColumns = structuredClone(columns);
        const clonedTable = table.cloneNode(false);
              clonedTable.append(tableHeader.cloneNode(true));
              clonedTable.append(tableBody.cloneNode(true));
              clonedTable.style.position = 'relative';
        const inserted = document.createElement('div');
              inserted.setAttribute('id', 'clonedTable')
              inserted.setAttribute('style','width:1px ;height:1px ;overflow:hidden ;clear:both;')
              inserted.append(clonedTable);

        tableContainer.insertBefore(inserted,table);
        const insertedTable = document.getElementById('clonedTable');
        if(insertedTable !== null)
        {
            const headerCells = insertedTable.querySelectorAll('th');
            const tableCells = insertedTable.querySelectorAll('td');

            tableCells.forEach((cell) => {
                cell.style.display = '';
            });

            headerCells.forEach((cell) => {
                cell.setAttribute('style', 'display:table-cell; min-width: 0px')
            });
            headerCells.forEach((column, index) => {
                if(['all','auto'].indexOf(resizeColumns[index].responsive.type) > -1) resizeColumns[index].minWidth = column.offsetWidth ? (column.offsetWidth + 10) : 0;
            });
            insertedTable.remove();
        }
        const visibleColumns = this.handleColumnsVisibility({
            columns: resizeColumns,
            tableContainer
        });

        return this.handleControl({
            columns: visibleColumns
        })
    },
    hasResponsiveDetails: (data) => {
        return data.every(function(item) {
            return (item.responsive !== undefined && item.responsive.details !== undefined && Object.keys(item.responsive.details).length > 0);
        });
    },
    handleSetVisibleColumns: (columns) => {
        return Array.isArray(columns[0]) ? columns.forEach((item) => {
            [...item].filter((column) => column.visible)
        }) : [...columns].filter((column) => column.visible)
    }
}

const rapidTables = {
    handleSort: ({
        columnIndex,
        sortColumnIndex,
        sortable = false,
        sortDirection,
        setSortDirection,
        setSortColumnIndex,
    }) => {
        if (!sortable) return;
        if (sortColumnIndex === columnIndex)
        {
            setSortDirection(sortDirection === 'desc' ? 'asc' : 'desc');
            setSortColumnIndex(sortDirection === 'desc' ? -1 : columnIndex);
        }
        else
        {
            setSortColumnIndex(columnIndex);
            setSortDirection('asc');
        }
    },
    handleColumnWidth: ({
        tableHeader,
        columns
    }) => {
        const tableHeaders = tableHeader ? Array.from(tableHeader.querySelectorAll('th')) : [];
        tableHeaders.map((item, index) => {
            columns[index].width = '';
            columns[index].width = item.scrollWidth !== 0 ? item.scrollWidth : columns[index].width;
        });
    },
    handleSearch: ({
        event,
        setSearchTerm,
        setCurrentPage
    }) => {
        setSearchTerm(event.target.value);
        setCurrentPage(1);
    },
    handleFilterData: ({
        dataArray,
        searchTerm
    }) => {
        return dataArray.filter(function(v) {
            const items = Object.values(v).map((x) => x.toString().toLowerCase());
            return items.filter((s) => s.includes(searchTerm.toLowerCase())).length > 0;
        })
    },
    handlePageLengthChange: ({
        value,
        setCurrentPage,
        setItemsPerPage
    }) => {
        setCurrentPage(1);
        setItemsPerPage(value);
    },
    handlePageChange: ({
        page,
        setCurrentPage
    }) => {
        setCurrentPage(page);
    },
    handleColumnDefaults: (columns, columnDefaults, columnsDefaults) => {
        columns.map((item) => {
            if(Array.isArray(item))
            {
                item.map((column) => {
                    utils.mergeNestedDefaults(column,columnDefaults,columnsDefaults);
                })
            }
            else
            {
                utils.mergeNestedDefaults(item,columnDefaults,columnsDefaults);
            }
        });
    },
    handleRowDefaults: (data, rowDefaults, rowsDefaults) => {
        data.map((row) => {
            utils.mergeNestedDefaults(row,rowDefaults,rowsDefaults);
        });
    }
}

export {
    utils,
    _responsive,
    rapidTables,
    defaults
}