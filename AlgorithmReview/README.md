# AlgorithmReview
---

## รายละเอียดโปรเจกต์

- Array
- Stack
- Queue
---

## โครงสร้างโปรเจกต์

```
AlgorithmReview/
│
├── README.md
└── src/
    ├── ArrayCaseStudy.java
    ├── StackCaseStudy.java
    └── QueueCaseStudy.java
```

# กรณีศึกษาที่ 1 : Array
### ระบบวิเคราะห์คะแนนสอบก่อนเรียนของนักศึกษา

### สิ่งที่โปรแกรมทำ
- คำนวณคะแนนรวม
- คำนวณคะแนนเฉลี่ย
- หาคะแนนสูงสุด
- หาคะแนนต่ำสุด
- นับจำนวนนักศึกษาที่ได้คะแนนตั้งแต่ 7 คะแนนขึ้นไป
- แสดงรายชื่อนักศึกษาที่ได้คะแนนต่ำกว่า 5 คะแนน

### ผลลัพธ์ตัวอย่าง

```
=== Result ===
Total Score = 62
Average Score = 6.2
Highest Score = 10
Lowest Score = 2
Students with score >= 7 = 5

Students who need more review
Student 3 : 4
Student 8 : 3
Student 10 : 2
```

### Time Complexity

การวนลูปเพื่อประมวลผลข้อมูลทั้งหมดใช้เวลา **O(n)**

---

# กรณีศึกษาที่ 2 : Stack
### ระบบ Undo ในโปรแกรมจดบันทึก

### สิ่งที่โปรแกรมทำ

- เพิ่มคำสั่งทั้งหมดลงใน Stack
- แสดงข้อมูลภายใน Stack
- Undo คำสั่งล่าสุด 2 ครั้ง
- แสดงคำสั่งที่ถูก Undo
- แสดงข้อมูลที่เหลือใน Stack
- ตรวจสอบว่า Stack ว่างก่อนใช้ pop()

### ผลลัพธ์ตัวอย่าง

```
Initial Stack
[Type Data, Type Structure, Delete Structure, Type Algorithm, Type Java]

Undo : Type Java
Undo : Type Algorithm

Stack After Undo
[Type Data, Type Structure, Delete Structure]
```

### หลักการทำงาน

Stack ใช้หลักการ **LIFO (Last In First Out)**

หมายถึง **ข้อมูลที่ถูกเพิ่มเข้ามาล่าสุด จะถูกนำออกก่อน** เช่น คำสั่ง "Type Java" ถูกเพิ่มเป็นลำดับสุดท้าย จึงถูก Undo ก่อน

### Time Complexity

- push() = O(1)
- pop() = O(1)
- peek() = O(1)

---

# กรณีศึกษาที่ 3 : Queue
### ระบบคิวผู้ป่วยในคลินิกสุขภาพ

### สิ่งที่โปรแกรมทำ

- เพิ่มผู้ป่วย P001 ถึง P005
- เรียกผู้ป่วยออกจากคิว 2 คน
- เพิ่มผู้ป่วย P006 และ P007
- แสดงผู้ป่วยคนถัดไป
- แสดงจำนวนผู้ป่วยที่รออยู่
- แสดงข้อมูลใน Queue
- ตรวจสอบว่า Queue ว่างก่อนใช้ remove()

### ผลลัพธ์ตัวอย่าง

```
Initial Queue
[P001, P002, P003, P004, P005]

Patients Served
P001
P002

Next Patient : P003
Patients Waiting : 5

Queue Status
[P003, P004, P005, P006, P007]
```

### หลักการทำงาน

Queue ใช้หลักการ **FIFO (First In First Out)**

หมายถึง **ผู้ที่เข้าคิวก่อน จะได้รับบริการก่อน** เช่น P001 และ P002 จะถูกเรียกก่อน P003

### Time Complexity

- add() = O(1)
- remove() = O(1)
- peek() = O(1)

---
