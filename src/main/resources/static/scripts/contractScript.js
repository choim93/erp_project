// ================================================== 변수들 세팅 ====================================================
    var rowCount = 1; // 추가되는 행의 개수를 저장하기 위한 변수

    // 공통 선택
    const businessPartnerInput = document.getElementById('businessPartnerInput');
    const employeeInput = document.getElementById('employeeInput');
    const storageInput = document.getElementById('storageInput');
    // 개별 선택
    const itemSelect = document.getElementById('myTableBody').rows[0].querySelector("#itemSelect");
    const itemQuantityNumberField = document.getElementById('myTableBody').rows[0].querySelector('#itemQuantityNumberField');


    const itemPriceTextField = document.getElementById('myTableBody').rows[0].querySelector('#itemPriceTextField');
    const totalPriceTextField = document.getElementById('myTableBody').rows[0].querySelector('#totalPriceTextField');

    // ================================================== ~ 변수들 세팅 ====================================================

    // ================================================== 행 추가 버튼 이벤트 처리 ====================================================
    function addRow() {
        var tableBody = document.getElementById("myTableBody");
        var newRow = tableBody.rows[0].cloneNode(true);
        tableBody.appendChild(newRow);
        rowCount++;

        // 각각의 행의 요소들에 대한 이벤트 리스너 함수 등록
        var addedRowsItemSelect = tableBody.rows[rowCount - 1].querySelector("#itemSelect");
        addedRowsItemSelect.addEventListener('change', onChangeUpdateItemPriceHandler);
        var addedRowsIntemQuantityField = tableBody.rows[rowCount - 1].querySelector("#itemQuantityNumberField")
        addedRowsIntemQuantityField.addEventListener('change', onChangeUpdateTotalPriceHandler);
    }
    document.getElementById('addRowButton').addEventListener('click', addRow);

    // ================================================== ~ 행 추가 버튼 이벤트 처리 ====================================================

    // ================================================== 아이템 선택 이벤트 처리 ====================================================
    function getItemPrice() {
        return new Promise((resolve, reject) => {
            fetch('/getItemDetails?itemId=' + itemSelect.value)
                .then(response => response.json())
                .then(itemDetails => {
                    // Use the received item details
                    resolve(itemDetails.itemPrice);
                })
                .catch(error => {
                    reject(error);
                });
        });
    }

    function onChangeUpdateItemPriceHandler(event) {
        var selectedItemId = event.target.value;  // 이벤트가 발생한 요소의 값을 가져옴

        // Make an AJAX request to the server to get item details
        fetch('/getItemDetails?itemId=' + selectedItemId)
            .then(response => response.json())
            .then(itemDetails => {
                // Use the received item details
                var itemPriceTextField = event.target.closest('tr').querySelector('#itemPriceTextField');
                itemPriceTextField.value = itemDetails.itemPrice;
            })
            .catch(error => console.error('Error fetching item details:', error));
    }

    itemSelect.addEventListener('change', onChangeUpdateItemPriceHandler);

    // ================================================== ~ 아이템 선택 이벤트 처리 ====================================================

    // ================================================== 개수 증감 이벤트 처리 ====================================================
    function onChangeUpdateTotalPriceHandler(event) {

        var totalPriceTextField = event.target.closest('tr').querySelector('#totalPriceTextField');
        var itemPriceTextField = event.target.closest('tr').querySelector('#itemPriceTextField');

        var quantity = event.target.closest('tr').querySelector('#itemQuantityNumberField').value;
        var price = itemPriceTextField.value;
        event.target.closest('tr').querySelector('#totalPriceTextField').value= price * quantity;
    }

    itemQuantityNumberField.addEventListener('change', onChangeUpdateTotalPriceHandler );
    // ================================================== ~ 개수 증감 이벤트 처리 ====================================================

    // ================================================== 시간 포맷 변경 함수 ====================================================
    function formatDate(date) {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1, 두 자리로 패딩
        const day = date.getDate().toString().padStart(2, '0'); // 두 자리로 패딩

        return `${year}-${month}-${day}`;
    }
    // ================================================== ~ 시간 포맷 변경 함수 ====================================================


    // ================================================== 필드 값들 뽑아와서 객체 반환하는 함수 ====================================================
    function collectInformationGetContractObject() {
        var tableBody = document.getElementById("myTableBody");
        var contractsList = [];

        for (var i = 0; i < tableBody.rows.length; i++) {
            var row = tableBody.rows[i];

            var businessPartnerInput = document.getElementById('businessPartnerInput');
            var employeeInput = document.getElementById('employeeInput');
            var storageInput = document.getElementById('storageInput');
            var itemSelect = row.querySelector('#itemSelect');
            var itemQuantityNumberField = row.querySelector('#itemQuantityNumberField');
            var contractTypeSelectField = document.getElementById("purchaseOrSell");

            var newContract = {
                isSelling: contractTypeSelectField.value,
                contractBusinessPartnerId: businessPartnerInput.value,
                contractStorageId: storageInput.value,
                contractItemId: itemSelect.value,
                contractResponsibleEmployeeId: employeeInput.value,
                contractItemQuantity: itemQuantityNumberField.value,
                dealDate: formatDate(new Date())
            };

            contractsList.push(newContract);
        }

        return contractsList;
    }
    // ================================================== ~ 필드 값들 뽑아와서 객체 반환하는 함수 ====================================================


    // ================================================== 객체 리스트 서버로 전송하는 함수 버튼 이벤트 등록 ====================================================
    function submitForm() {
        let contractInformation = collectInformationGetContractObject();
        let contractTypeSelectField = document.getElementById("purchaseOrSell");

        // contractsList를 JSON으로 변환하여 서버로 전송합니다.
        fetch(contractTypeSelectField.value === '1' ? '/contract/add_json/sell' : '/contract/add_json/purchase', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(contractInformation)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                // 서버 응답이 비어 있는지 확인
                const contentType = response.headers.get('content-type');
                if (contentType && contentType.indexOf('application/json') !== -1) {
                    return response.json(); // JSON이면 파싱
                } else {
                    return null; // JSON이 아니면 null 반환
                }
            })
            .then(data => {
                // data를 사용하여 추가 처리
                if (data) {
                    // 서버 응답이 JSON인 경우에 대한 처리
                } else {
                    // 서버 응답이 JSON이 아닌 경우에 대한 처리
                }
                // 페이지 전환 코드를 여기에 추가
                window.location.href = contractTypeSelectField.value === '1' ? '/contracts_sell' : '/contracts_purchase';
            })
            .catch(error => {
                console.error('Error:', error);
                // 오류 처리를 추가하려면 여기에 코드를 작성
            });
    }
    document.getElementById('submitContractUsingAjax').addEventListener("click", submitForm);

    // ================================================== ~ 객체 리스트 서버로 전송하는 함수 버튼 이벤트 등록 ====================================================
