
pragma solidity ^0.8.0;

contract Bank {
    mapping(address => uint) private balance;

    function deposit() public payable {
        require(msg.value > 0, "Deposit must be > 0");
        balance[msg.sender] += msg.value;
    }

    function withdraw(uint amount) public {
        require(balance[msg.sender] >= amount, "Insufficient balance");
        balance[msg.sender] -= amount;
        payable(msg.sender).transfer(amount);
    }

    function getBalance() public view returns (uint) {
        return balance[msg.sender];
    }
}