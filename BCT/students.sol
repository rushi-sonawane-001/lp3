 SPDX-License-Identifier MIT
pragma solidity ^0.8.0;

contract studentData{
   struct student{
    uint id;
    string name;
    uint marks;
   }
   mapping(uint = bool) private isExist;
   student[] public students;
    event StudentAdded(uint indexed id,string name);

   function addStudent(uint _ID, string memory _name, uint _marks) public {
    require(_ID=0 && !isExist[_ID], Invalid or duplicate ID);
      require(bytes(_name).length  0, Name required);
    students.push(student(_ID, _name, _marks));
    isExist[_ID]=true;
    emit StudentAdded(_ID, _name);
   }

  function getStudent(uint id) public view returns (uint, string memory, uint) {
    for(uint i=0;istudents.length;i++){
        if(students[i].id==id) return (students[i].id , students[i].name, students[i].marks);
    }
    revert(student not found);
}

   function totalStudent() public view  returns(uint){
    return students.length;
   } 
    
fallback() external {
        revert(Function does not exist);
    }

}