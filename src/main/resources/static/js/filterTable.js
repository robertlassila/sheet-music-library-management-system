document.addEventListener("DOMContentLoaded", function() {
    var filterInput = document.getElementById("filterInput");
    var table = document.getElementById("musicTable");

    if (!filterInput || !table) return;

    filterInput.addEventListener("input", function() {
        var filterValue = filterInput.value.toLowerCase();
        var rows = Array.from(table.tBodies[0].rows);

        rows.forEach(function(row) {
            var title = row.cells[0].innerText.toLowerCase();
            var composer = row.cells[1].innerText.toLowerCase();
            var ensemble = row.cells[2].innerText.toLowerCase();
            var genre = row.cells[3].innerText.toLowerCase();

            if (title.includes(filterValue) ||
                composer.includes(filterValue) ||
                ensemble.includes(filterValue) ||
                genre.includes(filterValue)) {
                row.style.display = ""; // show row
            } else {
                row.style.display = "none"; // hide row
            }
        });
    });
});