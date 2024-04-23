document.addEventListener('DOMContentLoaded', function () {
        var switchElement = document.getElementById('itemTypeSwitch');
        var labelElement = document.getElementById('itemType');

        switchElement.addEventListener('change', function () {
            var isChecked = switchElement.checked;
            var label = isChecked ? '원재료' : '제품';
            labelElement.textContent = '제품/원재료 구분 (' + label + ')';
        });
    });