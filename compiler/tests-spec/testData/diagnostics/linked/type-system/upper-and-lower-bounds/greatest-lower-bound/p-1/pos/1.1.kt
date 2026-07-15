// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, greatest-lower-bound -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: GLB enables access to members of all intersected types
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
interface Readable { fun read(): String }
interface Writable { fun write(s: String) }
class RW : Readable, Writable {
    override fun read() = "data"
    override fun write(s: String) {}
}
fun case_1(x: Any) {
    if (x is Readable && x is Writable) {
        x.read()
        x.write("x")
    }
}


// TESTCASE NUMBER: 2
fun case_2(rw: RW) {
    checkSubtype<Readable>(rw)
    checkSubtype<Writable>(rw)
}

interface X3 { val x: Int }
interface Y3 { val y: String }
class XY3 : X3, Y3 { override val x = 1; override val y = "y" }


// TESTCASE NUMBER: 3
fun case_3(v: Any) {
    if (v is X3 && v is Y3) {
        val sum = v.x + v.y.length
        checkSubtype<Int>(sum)
    }
}


// TESTCASE NUMBER: 4
fun case_4(x: Any) {
    if (x is Number && x is Comparable<*>) {
        checkSubtype<Number>(x)
        checkSubtype<Comparable<*>>(x)
    }
}

interface A5
interface B5
class AB5 : A5, B5


// TESTCASE NUMBER: 5
fun case_5(x: Any) {
    if (x is A5 && x is B5) {
        checkSubtype<A5>(x)
        checkSubtype<B5>(x)
    }
}
