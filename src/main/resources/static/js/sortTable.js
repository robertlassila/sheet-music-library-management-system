document.getElementById("sortSelect").addEventListener("change", function() {
                const colIndex = parseInt(this.value, 10);
                const table = document.getElementById("musicTable").tBodies[0];
                const rows = Array.from(table.rows);

                rows.sort((a, b) => {
                    return a.cells[colIndex].innerText.localeCompare(b.cells[colIndex].innerText);
                });

                rows.forEach(row => table.appendChild(row));
                });