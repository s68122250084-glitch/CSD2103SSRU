# ข้อที่ 1 : การกลับลำดับ String (Reverse String)

---
## วัตถุประสงค์

เขียนโปรแกรมภาษา Java เพื่อสร้าง String ใหม่ที่มีลำดับตัวอักษรกลับจาก String เดิม โดยออกแบบอัลกอริทึมอย่างน้อย 2 วิธี ได้แก่

1. Recursive Algorithm
2. Iterative Algorithm

---

## ตัวอย่างการทำงาน

### Input

```
pots&pans
```

### Output

```
snap&stop
```

---
# Algorithm 1 : Recursive Algorithm

## แนวคิด

ใช้อัลกอริทึมแบบเวียนเกิด (Recursion) โดยนำตัวอักษรตัวสุดท้ายของ String มาต่อกับผลลัพธ์ของการเรียกเมธอดกับ String ส่วนที่เหลือ จนกระทั่งเหลือ String ที่มีความยาว 0 หรือ 1 ตัวอักษร ซึ่งเป็น Base Case

### Pseudocode

```
ReverseRecursive(s)

if s.length <= 1
    return s

return lastCharacter(s) +
       ReverseRecursive(remainingString)
```

### Time Complexity

```
O(n²)
```

เนื่องจากมีการเรียก Recursion n ครั้ง และการต่อ String ด้วยเครื่องหมาย `+` จะสร้าง String ใหม่ทุกครั้ง

### Space Complexity

```
O(n)
```

เกิดจากการใช้ Call Stack ของ Recursion

### ข้อดี

- โค้ดสั้น
- เข้าใจหลักการ Recursion ได้ง่าย

### ข้อจำกัด

- ใช้หน่วยความจำมาก
- เสี่ยงเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่
- การต่อ String ด้วย `+` ทำให้ประสิทธิภาพลดลง

---

# Algorithm 2 : Iterative Algorithm

## แนวคิด

ใช้ลูป for อ่านตัวอักษรจากตำแหน่งสุดท้ายย้อนกลับมายังตำแหน่งแรก แล้วนำมาต่อกันด้วย StringBuilder

### Pseudocode

```
ReverseIterative(s)

create result

for i = lastIndex downto 0
    append s[i]

return result
```

### Time Complexity

```
O(n)
```

เนื่องจากอ่านตัวอักษรเพียงครั้งเดียว

### Space Complexity

```
O(n)
```

ใช้พื้นที่เก็บผลลัพธ์ใน StringBuilder

### ข้อดี

- ทำงานรวดเร็ว
- ใช้หน่วยความจำน้อยกว่า Recursion
- เหมาะกับข้อมูลขนาดใหญ่

### ข้อจำกัด

- โค้ดยาวกว่า Recursive เล็กน้อย

---

# เปรียบเทียบอัลกอริทึม

| หัวข้อ | Recursive | Iterative |
|---------|-----------|-----------|
| Time Complexity | O(n²) | O(n) |
| Space Complexity | O(n) | O(n) |
| ความเร็ว | ช้ากว่า | เร็วกว่า |
| StackOverflow | อาจเกิด | ไม่เกิด |
| เหมาะกับข้อมูลขนาดใหญ่ | ❌ | ✅ |

---

# การวิเคราะห์เพิ่มเติม

## จำนวนครั้งที่ประมวลผลตัวอักษร

- Recursive : n ครั้ง
- Iterative : n ครั้ง

## ผลกระทบของการใช้เครื่องหมาย +

String เป็น Immutable ดังนั้นทุกครั้งที่ใช้ `+` จะสร้างอ็อบเจ็กต์ใหม่ ส่งผลให้ใช้เวลาและหน่วยความจำมากขึ้น

## String กับ StringBuilder

| String | StringBuilder |
|---------|---------------|
| Immutable | Mutable |
| ใช้ + สร้าง Object ใหม่ | ใช้ append() |
| ช้ากว่า | เร็วกว่า |

---

# ผลการทดลอง

| ขนาดข้อมูล | Recursive | Iterative |
|------------|-----------|-----------|
| 10 | ทำงานได้ | ทำงานได้ |
| 100 | ทำงานได้ | ทำงานได้ |
| 1,000 | เริ่มช้าลง | ยังทำงานได้ดี |
| 10,000 | อาจเกิด StackOverflowError | ทำงานได้ปกติ |

---

# สรุป

Recursive Algorithm เหมาะสำหรับการศึกษาแนวคิดของการเวียนเกิด (Recursion) แต่เมื่อข้อมูลมีขนาดใหญ่ ประสิทธิภาพจะลดลงเนื่องจากการเรียกเมธอดซ้ำและการต่อ String ด้วยเครื่องหมาย `+`

Iterative Algorithm ที่ใช้ StringBuilder มีประสิทธิภาพดีกว่า ใช้เวลา O(n) และไม่เสี่ยงเกิด StackOverflowError จึงเหมาะสำหรับการใช้งานจริง

---

# ข้อที่ 2 : การตรวจสอบ Palindrome

---

## วัตถุประสงค์

เขียนโปรแกรมภาษา Java เพื่อตรวจสอบว่า String ที่รับเข้ามาเป็น **Palindrome** หรือไม่ โดยออกแบบอัลกอริทึมอย่างน้อย 2 วิธี ได้แก่

1. Reverse and Compare
2. Recursive Two-Pointer

**หมายเหตุ**
โปรแกรมต้องสามารถละเว้น
- ตัวพิมพ์เล็กและตัวพิมพ์ใหญ่
- ช่องว่าง
- เครื่องหมายวรรคตอน

---

## ตัวอย่างการทำงาน

### Input

```
racecar
```

Output

```
true
```

### Input

```
algorithm
```

Output

```
false
```

### Input

```
A man, a plan, a canal: Panama
```

Output

```
true
```

---

# Algorithm 1 : Reverse and Compare

## แนวคิด

สร้าง String ที่กลับลำดับจาก String เดิม แล้วนำมาเปรียบเทียบกับ String ต้นฉบับ หากทั้งสอง String เหมือนกัน แสดงว่าเป็น Palindrome

ก่อนตรวจสอบจะทำการแปลงตัวอักษรเป็นตัวพิมพ์เล็ก และลบช่องว่างกับเครื่องหมายวรรคตอนออก

### Pseudocode

```
Normalize(s)

reverse = Reverse(s)

if reverse equals s
    return true
else
    return false
```

### Time Complexity

```
O(n)
```

ใช้เวลา O(n) ในการกลับ String และ O(n) ในการเปรียบเทียบ ดังนั้นยังเป็น O(n)

### Space Complexity

```
O(n)
```

เนื่องจากต้องสร้าง String ใหม่

### ข้อดี

- เข้าใจง่าย
- เขียนโปรแกรมไม่ซับซ้อน

### ข้อจำกัด

- ต้องใช้พื้นที่เพิ่มสำหรับ String ที่กลับลำดับ

---

# Algorithm 2 : Recursive Two-Pointer

## แนวคิด

ใช้ตัวชี้สองตำแหน่ง

- left เริ่มต้นทางซ้าย
- right เริ่มต้นทางขวา

เปรียบเทียบตัวอักษรทั้งสองด้าน

- ถ้าไม่เท่ากัน คืนค่า false
- ถ้าเท่ากัน เรียกเมธอดแบบ Recursion เพื่อตรวจสอบตัวถัดไป

จนกว่า left จะมากกว่าหรือเท่ากับ right

### Pseudocode

```
Palindrome(left,right)

if left >= right
    return true

if s[left] != s[right]
    return false

return Palindrome(left+1,right-1)
```

### Time Complexity

```
O(n)
```

ตรวจสอบตัวอักษรประมาณครึ่งหนึ่งของ String

### Space Complexity

```
O(n)
```

เกิดจาก Call Stack ของ Recursion

### ข้อดี

- ไม่ต้องสร้าง String ใหม่
- สามารถหยุดทำงานได้ทันทีเมื่อพบตัวอักษรไม่ตรงกัน

### ข้อจำกัด

- เสี่ยงเกิด StackOverflowError หาก String มีขนาดใหญ่มาก

---

# เปรียบเทียบอัลกอริทึม

| หัวข้อ | Reverse & Compare | Recursive |
|---------|-------------------|-----------|
| Time Complexity | O(n) | O(n) |
| Space Complexity | O(n) | O(n) |
| สร้าง String ใหม่ | ✔ | ✘ |
| หยุดก่อนครบทุกตัวอักษร | ✘ | ✔ |
| เสี่ยง StackOverflow | ✘ | ✔ |

---

# การวิเคราะห์เพิ่มเติม

## กรณี String เป็น Palindrome

ทั้งสองอัลกอริทึมต้องตรวจสอบตัวอักษรทุกตัว

Time Complexity

```
O(n)
```

---

## กรณีตัวอักษรคู่แรกไม่ตรงกัน

### Reverse and Compare

ยังต้องสร้าง String กลับลำดับทั้งหมดก่อน

```
O(n)
```

### Recursive Two-Pointer

เปรียบเทียบครั้งแรกแล้วหยุดทันที

```
Best Case = O(1)
```

---

## Best-case Time Complexity

| Algorithm | Complexity |
|------------|------------|
| Reverse & Compare | O(n) |
| Recursive | O(1) |

---

## Worst-case Time Complexity

| Algorithm | Complexity |
|------------|------------|
| Reverse & Compare | O(n) |
| Recursive | O(n) |

---

## Space Complexity

ทั้งสองวิธี

```
O(n)
```

แต่ใช้พื้นที่คนละลักษณะ

- Reverse ใช้พื้นที่เก็บ String ใหม่
- Recursive ใช้ Call Stack

---

## ความสามารถในการหยุดทำงานก่อนครบทุกตัวอักษร

Reverse and Compare

- ไม่สามารถหยุดได้
- ต้องสร้าง String ใหม่ก่อนเสมอ

Recursive Two-Pointer

- สามารถหยุดได้ทันทีเมื่อพบตัวอักษรไม่ตรงกัน

---

# ผลการทดลอง

| ขนาดข้อมูล | Reverse | Recursive |
|------------|---------|-----------|
| 10 | ทำงานได้ | ทำงานได้ |
| 100 | ทำงานได้ | ทำงานได้ |
| 1,000 | ทำงานได้ | ทำงานได้ |
| 10,000 | ทำงานได้ | อาจเกิด StackOverflowError |

---

# สรุป

Reverse and Compare เหมาะสำหรับโปรแกรมทั่วไป เนื่องจากเขียนง่ายและไม่เสี่ยงเกิด StackOverflowError

Recursive Two-Pointer มีข้อดีคือสามารถหยุดการทำงานได้ทันทีเมื่อพบตัวอักษรไม่ตรงกัน จึงมี Best-case เป็น O(1) แต่ไม่เหมาะกับข้อมูลขนาดใหญ่มาก เพราะอาจเกิด StackOverflowError ได้

---

# ข้อที่ 3 : การเปรียบเทียบจำนวนสระและพยัญชนะ

---

# วัตถุประสงค์

เขียนโปรแกรมภาษา Java เพื่อตรวจสอบว่าสตริงภาษาอังกฤษมีจำนวน **สระ (a, e, i, o, u)** มากกว่าจำนวนพยัญชนะหรือไม่ โดยออกแบบอัลกอริทึมอย่างน้อย 2 วิธี ได้แก่

1. Recursive Counting
2. Iterative Counting

**เงื่อนไข**

- ไม่นับตัวเลข
- ไม่นับช่องว่าง
- ไม่นับเครื่องหมายพิเศษ
- ไม่แยกตัวพิมพ์เล็กและตัวพิมพ์ใหญ่

---

# ตัวอย่างการทำงาน

### Input

```
education
```

### Output

```
Vowels : 5
Consonants : 4
Result : true
```

---

# Algorithm 1 : Recursive Counting

## แนวคิด

ใช้อัลกอริทึมแบบเวียนเกิด (Recursion) ตรวจสอบตัวอักษรทีละตัว หากเป็นสระให้นับจำนวนสระ หากเป็นพยัญชนะให้นับจำนวนพยัญชนะ จากนั้นเรียกเมธอดกับตัวอักษรถัดไปจนตรวจสอบครบทุกตัว แล้วเปรียบเทียบว่าจำนวนสระมากกว่าพยัญชนะหรือไม่

### Base Case

เมื่ออ่านครบทุกตัวอักษร (index เท่ากับความยาวของ String) ให้คืนค่าผลการเปรียบเทียบ

### Recursive Case

ตรวจสอบตัวอักษรปัจจุบัน แล้วเรียกเมธอดกับตำแหน่งถัดไป

### Pseudocode

```
HasMoreVowelsRecursive(s,index,vowel,consonant)

if index == length(s)
    return vowel > consonant

character = lowercase(s[index])

if character is vowel
    vowel++

else if character is letter
    consonant++

return HasMoreVowelsRecursive(s,index+1,vowel,consonant)
```

### Time Complexity

```
O(n)
```

เนื่องจากตรวจสอบตัวอักษรทุกตัวเพียงครั้งเดียว

### Space Complexity

```
O(n)
```

เกิดจากการใช้ Call Stack ของ Recursion

### ข้อดี

- โค้ดสั้น
- เข้าใจหลักการ Recursion
- เหมาะสำหรับการเรียนรู้

### ข้อจำกัด

- ใช้หน่วยความจำมากกว่า
- อาจเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่มาก

---

# Algorithm 2 : Iterative Counting

## แนวคิด

ใช้ลูป for อ่านตัวอักษรทุกตัว หากเป็นสระให้นับจำนวนสระ หากเป็นพยัญชนะให้นับจำนวนพยัญชนะ เมื่ออ่านครบทุกตัวแล้วจึงเปรียบเทียบว่าจำนวนสระมากกว่าพยัญชนะหรือไม่

### Pseudocode

```
HasMoreVowelsIterative(s)

vowel = 0
consonant = 0

for each character in s

    if character is vowel
        vowel++

    else if character is letter
        consonant++

return vowel > consonant
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(1)
```

ใช้เพียงตัวแปรนับจำนวนสระและพยัญชนะ

### ข้อดี

- ทำงานรวดเร็ว
- ใช้หน่วยความจำน้อย
- ไม่เสี่ยงเกิด StackOverflowError
- เหมาะกับข้อมูลขนาดใหญ่

### ข้อจำกัด

- โค้ดยาวกว่าแบบ Recursion เล็กน้อย

---

# เปรียบเทียบอัลกอริทึม

| หัวข้อ | Recursive | Iterative |
|---------|-----------|-----------|
| Time Complexity | O(n) | O(n) |
| Space Complexity | O(n) | O(1) |
| ความเร็ว | ปานกลาง | เร็วกว่า |
| StackOverflowError | อาจเกิด | ไม่เกิด |
| เหมาะกับข้อมูลขนาดใหญ่ | ❌ | ✅ |

---

# การวิเคราะห์เพิ่มเติม

## จำนวน Recursive Calls

จำนวนการเรียกเมธอดแบบ Recursion จะเท่ากับจำนวนตัวอักษรใน String หรือประมาณ **n ครั้ง**

---

## ความเสี่ยงของ StackOverflowError

Recursive Counting ใช้ Call Stack ทุกครั้งที่เรียกเมธอด หาก String มีขนาดใหญ่มาก อาจทำให้เกิด StackOverflowError ได้

ส่วน Iterative Counting ใช้ลูป จึงไม่เกิดปัญหานี้

---

## ขนาดข้อมูลที่เหมาะสม

| Algorithm | เหมาะกับข้อมูล |
|-----------|----------------|
| Recursive Counting | ขนาดเล็กถึงปานกลาง |
| Iterative Counting | ทุกขนาด โดยเฉพาะข้อมูลขนาดใหญ่ |

---

# ผลการทดลอง

| ขนาดข้อมูล | Recursive | Iterative |
|------------|-----------|-----------|
| 10 | ทำงานได้ | ทำงานได้ |
| 100 | ทำงานได้ | ทำงานได้ |
| 1,000 | ทำงานได้ | ทำงานได้ |
| 10,000 | อาจเกิด StackOverflowError | ทำงานได้ปกติ |

---

# สรุป

Recursive Counting เหมาะสำหรับการเรียนรู้หลักการเวียนเกิด (Recursion) และการแก้ปัญหาแบบแบ่งย่อย แต่มีข้อจำกัดด้านหน่วยความจำและอาจเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่

Iterative Counting เหมาะสำหรับการใช้งานจริง เนื่องจากมี Time Complexity เท่ากันคือ **O(n)** แต่ใช้ Space Complexity เพียง **O(1)** ทำงานได้รวดเร็วกว่า และรองรับข้อมูลขนาดใหญ่ได้ดีกว่า

---

# ข้อที่ 4 : การจัดกลุ่มจำนวนคู่และจำนวนคี่

---

# วัตถุประสงค์

เขียนโปรแกรมภาษา Java เพื่อจัดเรียงอาร์เรย์ของจำนวนเต็ม โดยให้

- จำนวนคู่ทั้งหมดอยู่ด้านหน้า
- จำนวนคี่ทั้งหมดอยู่ด้านหลัง

ไม่จำเป็นต้องเรียงค่าภายในแต่ละกลุ่ม

ออกแบบอัลกอริทึม 3 วิธี ได้แก่

1. Recursive Two-Pointer
2. Iterative Two-Pointer
3. Extra Array

---

# ตัวอย่างการทำงาน

### Input

```
[7, 2, 9, 4, 1, 6, 3, 8]
```

### Output

```
[8, 2, 6, 4, 1, 9, 3, 7]
```

หรือผลลัพธ์อื่นที่จำนวนคู่อยู่ด้านหน้า และจำนวนคี่อยู่ด้านหลังก็ถือว่าถูกต้อง

---

# Algorithm 1 : Recursive Two-Pointer

## แนวคิด

ใช้ตัวชี้สองตำแหน่ง คือ left และ right

- ถ้าด้านซ้ายเป็นเลขคู่ ให้เลื่อน left
- ถ้าด้านขวาเป็นเลขคี่ ให้เลื่อน right
- ถ้าซ้ายเป็นเลขคี่และขวาเป็นเลขคู่ ให้สลับค่า
- ทำซ้ำด้วย Recursion จน left มากกว่าหรือเท่ากับ right

### Base Case

```
left >= right
```

### Recursive Case

ตรวจสอบและสลับข้อมูล จากนั้นเรียกเมธอดกับช่วงข้อมูลที่เหลือ

### Pseudocode

```
Recursive(left,right)

if left >= right
    return

while left เป็นเลขคู่
    left++

while right เป็นเลขคี่
    right--

swap(left,right)

Recursive(left,right)
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(n)
```

เนื่องจากใช้ Call Stack

### ข้อดี

- ไม่ต้องสร้างอาร์เรย์ใหม่
- ใช้หน่วยความจำเพิ่มเติมน้อย

### ข้อจำกัด

- อาจเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่

---

# Algorithm 2 : Iterative Two-Pointer

## แนวคิด

ใช้หลักการเดียวกับ Recursive แต่ใช้ลูป while แทนการเรียกเมธอดซ้ำ

### Pseudocode

```
left = 0
right = n-1

while left < right

    เลื่อน left

    เลื่อน right

    swap
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(1)
```

### ข้อดี

- ทำงานเร็ว
- ไม่เกิด StackOverflowError
- ใช้หน่วยความจำต่ำ

### ข้อจำกัด

- ไม่รักษาลำดับเดิมของข้อมูล

---

# Algorithm 3 : Extra Array

## แนวคิด

สร้างอาร์เรย์ใหม่

รอบแรกเก็บเลขคู่ทั้งหมด

รอบที่สองเก็บเลขคี่ทั้งหมด

คืนค่าอาร์เรย์ใหม่

### Pseudocode

```
สร้าง array ใหม่

เพิ่มเลขคู่ทั้งหมด

เพิ่มเลขคี่ทั้งหมด

return array ใหม่
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(n)
```

### ข้อดี

- เข้าใจง่าย
- สามารถรักษาลำดับเดิมของข้อมูล (Stable)

### ข้อจำกัด

- ใช้หน่วยความจำเพิ่ม

---

# เปรียบเทียบอัลกอริทึม

| หัวข้อ | Recursive | Iterative | Extra Array |
|---------|-----------|-----------|-------------|
| Time Complexity | O(n) | O(n) | O(n) |
| Space Complexity | O(n) | O(1) | O(n) |
| In-place | ✅ | ✅ | ❌ |
| Stable | ❌ | ❌ | ✅ |
| StackOverflow | อาจเกิด | ไม่เกิด | ไม่เกิด |

---

# การวิเคราะห์เพิ่มเติม

## จำนวนครั้งของการสลับข้อมูล

- Recursive Two-Pointer : ขึ้นอยู่กับตำแหน่งของข้อมูล
- Iterative Two-Pointer : ขึ้นอยู่กับตำแหน่งของข้อมูล
- Extra Array : ไม่มีการสลับข้อมูล

---

## การเปลี่ยนแปลงอาร์เรย์เดิม

| Algorithm | เปลี่ยนอาร์เรย์เดิม |
|------------|----------------|
| Recursive | ใช่ |
| Iterative | ใช่ |
| Extra Array | ไม่ใช่ |

---

## Stable Algorithm

Recursive Two-Pointer และ Iterative Two-Pointer ไม่รักษาลำดับเดิมของสมาชิก

Extra Array รักษาลำดับเดิมของข้อมูลได้ (Stable)

ตัวอย่าง

Input

```
[5,2,7,4,9,6]
```

Output

```
[2,4,6,5,7,9]
```

---

# สรุป

Recursive Two-Pointer เหมาะสำหรับการศึกษาหลักการ Recursion และการทำงานแบบ In-place แต่มีข้อจำกัดด้านหน่วยความจำ

Iterative Two-Pointer เหมาะกับการใช้งานจริง เพราะใช้หน่วยความจำเพียง O(1) และไม่เสี่ยงเกิด StackOverflowError

Extra Array เหมาะเมื่อจำเป็นต้องรักษาลำดับเดิมของข้อมูล (Stable) แม้ว่าจะต้องใช้หน่วยความจำเพิ่มเติม

---

# ข้อที่ 5 : การแบ่งอาร์เรย์ตามค่า k

---

# วัตถุประสงค์

เขียนโปรแกรมภาษา Java เพื่อจัดตำแหน่งสมาชิกในอาร์เรย์ โดยให้

- สมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ k อยู่ด้านหน้า
- สมาชิกที่มีค่ามากกว่า k อยู่ด้านหลัง

โดยออกแบบอัลกอริทึม 3 วิธี ได้แก่

1. Recursive Partition
2. Iterative Partition
3. Sorting-Based Algorithm

---

# ตัวอย่างการทำงาน

### Input

```
A = [12, 4, 7, 15, 3, 10, 8]
k = 8
```

### Output

```
[8, 4, 7, 3, 15, 10, 12]
```

ผลลัพธ์อาจแตกต่างกันได้ แต่ข้อมูลที่มีค่าน้อยกว่าหรือเท่ากับ k ต้องอยู่ด้านหน้า และค่าที่มากกว่า k ต้องอยู่ด้านหลัง

---

# Algorithm 1 : Recursive Partition

## แนวคิด

ใช้อัลกอริทึมแบบเวียนเกิด (Recursion) ร่วมกับตัวชี้สองตำแหน่ง (left และ right)

- หาก A[left] ≤ k ให้เลื่อน left
- หาก A[right] > k ให้เลื่อน right
- หากพบข้อมูลที่อยู่ผิดด้าน ให้สลับข้อมูล
- เรียกเมธอดแบบ Recursion จนกว่า left จะมากกว่าหรือเท่ากับ right

### Base Case

```
left >= right
```

### Recursive Case

ตรวจสอบและสลับข้อมูล จากนั้นเรียกเมธอดกับช่วงข้อมูลที่เหลือ

### Pseudocode

```
Partition(left,right)

if left >= right
    return

while A[left] <= k
    left++

while A[right] > k
    right--

swap(A[left], A[right])

Partition(left,right)
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(n)
```

### ข้อดี

- ไม่ต้องสร้างอาร์เรย์ใหม่
- เป็น In-place Algorithm

### ข้อจำกัด

- ใช้หน่วยความจำจาก Call Stack
- อาจเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่มาก

---

# Algorithm 2 : Iterative Partition

## แนวคิด

ใช้ตัวชี้สองตำแหน่งเหมือน Recursive แต่ใช้ลูป while แทนการเรียกเมธอดแบบเวียนเกิด

### Pseudocode

```
left = 0
right = n-1

while left < right

    while A[left] <= k
        left++

    while A[right] > k
        right--

    swap(A[left], A[right])
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(1)
```

### ข้อดี

- ทำงานรวดเร็ว
- ใช้หน่วยความจำน้อย
- ไม่เกิด StackOverflowError
- เป็น In-place Algorithm

### ข้อจำกัด

- ไม่รักษาลำดับเดิมของข้อมูล

---

# Algorithm 3 : Sorting-Based Algorithm

## แนวคิด

เรียงลำดับข้อมูลทั้งหมดก่อน จากนั้นค้นหาตำแหน่งสุดท้ายของสมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ k ทำให้สมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ k อยู่ด้านหน้า และสมาชิกที่มากกว่า k อยู่ด้านหลัง

### Pseudocode

```
Sort(A)

for ทุกสมาชิกใน A

    ถ้า A[i] <= k

        เก็บตำแหน่ง

แสดงผลอาร์เรย์หลังเรียง
```

### Time Complexity

```
O(n log n)
```

### Space Complexity

```
O(1)
```

*(หากใช้การเรียงลำดับแบบ In-place เช่น Heap Sort)*

### ข้อดี

- เขียนโปรแกรมได้ง่าย
- ได้ข้อมูลที่เรียงลำดับแล้ว

### ข้อจำกัด

- ใช้เวลามากกว่าวิธี Partition
- การเรียงลำดับทั้งหมดไม่จำเป็นสำหรับโจทย์นี้

---

# เปรียบเทียบอัลกอริทึม

| Algorithm | Time Complexity | Space Complexity | In-place |
|-----------|-----------------|------------------|----------|
| Recursive Partition | O(n) | O(n) | ✓ |
| Iterative Partition | O(n) | O(1) | ✓ |
| Sorting-Based | O(n log n) | O(1)* | แล้วแต่การเรียงที่ใช้ |

---

# การวิเคราะห์เพิ่มเติม

## เหตุผลที่การเรียงลำดับอาจทำให้โปรแกรมช้ากว่าที่จำเป็น

โจทย์ต้องการเพียงแบ่งข้อมูลออกเป็น 2 กลุ่ม คือ

- ค่าน้อยกว่าหรือเท่ากับ k
- ค่ามากกว่า k

จึงไม่จำเป็นต้องเรียงข้อมูลทั้งหมด การใช้ Sorting ทำให้เสียเวลาเพิ่มเป็น O(n log n) ในขณะที่การใช้ Partition ใช้เวลาเพียง O(n)

---

## ความสัมพันธ์กับ Quick Sort

Recursive Partition และ Iterative Partition ใช้หลักการเดียวกับขั้นตอน Partition ของอัลกอริทึม Quick Sort คือแบ่งข้อมูลออกเป็นสองส่วนตามเงื่อนไข โดยไม่จำเป็นต้องเรียงข้อมูลทั้งหมด

---

## In-place Algorithm

| Algorithm | In-place |
|-----------|----------|
| Recursive Partition | ✓ |
| Iterative Partition | ✓ |
| Sorting-Based | ขึ้นอยู่กับอัลกอริทึมที่ใช้ในการเรียง |

---

# สรุป

- **Recursive Partition** เหมาะสำหรับการศึกษาหลักการ Recursion และการทำงานแบบ In-place แต่ใช้หน่วยความจำมากกว่า
- **Iterative Partition** เหมาะสำหรับการใช้งานจริง เพราะมี Time Complexity O(n) ใช้ Space Complexity เพียง O(1) และไม่เสี่ยงเกิด StackOverflowError
- **Sorting-Based Algorithm** เหมาะเมื่อจำเป็นต้องเรียงข้อมูลทั้งหมด แต่สำหรับโจทย์นี้มีประสิทธิภาพน้อยกว่า เพราะใช้เวลา O(n log n) ทั้งที่การแบ่งข้อมูลสามารถทำได้ใน O(n)

---

# ข้อที่ 6 : การค้นหาคู่จำนวนที่มีผลรวมเท่ากับ k

---

# วัตถุประสงค์

เขียนโปรแกรมภาษา Java เพื่อค้นหาสมาชิก 2 ตัวในอาร์เรย์ที่มีผลรวมเท่ากับค่า k โดยกำหนดให้อาร์เรย์เรียงลำดับจากน้อยไปมาก

ออกแบบอัลกอริทึม 3 วิธี ได้แก่

1. Brute Force
2. Recursive Two-Pointer
3. Binary Search

---

# ตัวอย่างการทำงาน

### Input

```
A = [2, 4, 7, 11, 15, 20]
k = 18
```

### Output

```
Pair found : 7 and 11
```

---

# Algorithm 1 : Brute Force

## แนวคิด

ตรวจสอบสมาชิกทุกคู่ที่เป็นไปได้ โดยใช้ลูปซ้อนกัน 2 ชั้น หากพบคู่ที่มีผลรวมเท่ากับ k ให้รายงานผลทันที

### Pseudocode

```
for i = 0 ถึง n-1

    for j = i+1 ถึง n-1

        if A[i] + A[j] == k

            return true

return false
```

### Time Complexity

```
O(n²)
```

### Space Complexity

```
O(1)
```

### ข้อดี

- เข้าใจง่าย
- ใช้ได้กับข้อมูลทุกแบบ
- ไม่ต้องเรียงข้อมูล

### ข้อจำกัด

- ใช้เวลามากเมื่อข้อมูลมีขนาดใหญ่

---

# Algorithm 2 : Recursive Two-Pointer

## แนวคิด

ใช้อาร์เรย์ที่เรียงลำดับแล้ว กำหนดตัวชี้สองตำแหน่ง

- left อยู่ต้นอาร์เรย์
- right อยู่ท้ายอาร์เรย์

คำนวณผลรวม

- เท่ากับ k → พบคำตอบ
- น้อยกว่า k → เลื่อน left
- มากกว่า k → เลื่อน right

ใช้ Recursion ทำซ้ำจนกว่าจะพบคำตอบ

### Base Case

```
left >= right
```

### Recursive Case

```
sum = A[left] + A[right]

if sum == k

    return true

if sum < k

    Recursive(left+1,right)

else

    Recursive(left,right-1)
```

### Time Complexity

```
O(n)
```

### Space Complexity

```
O(n)
```

### ข้อดี

- เร็วกว่าวิธี Brute Force
- เปรียบเทียบน้อยกว่า

### ข้อจำกัด

- ต้องใช้อาร์เรย์ที่เรียงลำดับแล้ว
- อาจเกิด StackOverflowError

---

# Algorithm 3 : Binary Search

## แนวคิด

เลือกสมาชิกทีละตัว แล้วใช้ Binary Search ค้นหาค่าที่เหลือ (k - A[i]) ในช่วงข้อมูลด้านหลัง

หากพบ แสดงว่ามีคู่ที่ผลรวมเท่ากับ k

### Pseudocode

```
for i = 0 ถึง n-1

    target = k - A[i]

    BinarySearch(target)

    if found

        return true

return false
```

### Time Complexity

```
O(n log n)
```

### Space Complexity

```
O(1)
```

### ข้อดี

- เร็วกว่าวิธี Brute Force
- ใช้ Binary Search ซึ่งมีประสิทธิภาพ

### ข้อจำกัด

- ต้องใช้อาร์เรย์ที่เรียงลำดับแล้ว
- ช้ากว่า Two-Pointer

---

# เปรียบเทียบอัลกอริทึม

| Algorithm | Time | Space | ข้อดี | ข้อจำกัด |
|------------|------|-------|--------|-----------|
| Brute Force | O(n²) | O(1) | เข้าใจง่าย ใช้ได้ทุกข้อมูล | ช้าที่สุด |
| Recursive Two-Pointer | O(n) | O(n) | เร็วที่สุด | ต้องเรียงข้อมูลและใช้ Recursion |
| Binary Search | O(n log n) | O(1) | เร็วกว่า Brute Force | ต้องเรียงข้อมูล |

---

# การวิเคราะห์เพิ่มเติม

## เหตุใด Two-Pointer จึงใช้ได้เมื่ออาร์เรย์เรียงลำดับแล้ว

เพราะเมื่ออาร์เรย์เรียงลำดับ หากผลรวมน้อยกว่า k สามารถเลื่อนตัวชี้ซ้ายเพื่อเพิ่มผลรวมได้ และหากผลรวมมากกว่า k สามารถเลื่อนตัวชี้ขวาเพื่อลดผลรวมได้ จึงลดจำนวนการค้นหาได้มาก

---

## หากอาร์เรย์ยังไม่เรียงลำดับ

Two-Pointer และ Binary Search จะไม่สามารถทำงานได้อย่างถูกต้อง เนื่องจากการเลื่อนตัวชี้หรือค้นหาแบบ Binary Search อาศัยข้อมูลที่เรียงลำดับแล้ว

---

# สรุป

- **Brute Force** เหมาะกับข้อมูลขนาดเล็ก หรือข้อมูลที่ยังไม่เรียงลำดับ เพราะใช้งานง่าย แต่ใช้เวลา O(n²)
- **Recursive Two-Pointer** เหมาะที่สุดเมื่ออาร์เรย์เรียงลำดับแล้ว เพราะใช้เวลาเพียง O(n) แต่มีข้อจำกัดเรื่องการใช้ Recursion
- **Binary Search** เหมาะสำหรับอาร์เรย์ที่เรียงลำดับแล้ว และมีประสิทธิภาพดีกว่า Brute Force โดยใช้เวลา O(n log n) แต่ยังช้ากว่า Two-Pointer
