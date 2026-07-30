// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 282 -> sentence 282
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 282 -> sentence 282
 *                declarations, property-declaration -> paragraph 282 -> sentence 282
 *                declarations, property-declaration, getters-and-setters -> paragraph 282 -> sentence 282
 * NUMBER: 1
 * DESCRIPTION: precise types when reading a var with private set from outside the declaring class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Counter {
    var count: Int = 0
        private set

    fun inc() {
        count++
    }
}

fun case1() {
    val c = Counter()
    c checkType { check<Counter>() }
    c.count checkType { check<Int>() }
    c.inc()
    c.count checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class LabelBox {
    var label: String = "x"
        private set

    fun rename(next: String) {
        label = next
    }
}

fun case2() {
    val box = LabelBox()
    box.label checkType { check<String>() }
    box.rename("ok")
    box.label checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class FlagHolder {
    var flag: Boolean = false
        private set

    fun toggle() {
        flag = !flag
    }
}

fun case3() {
    val f = FlagHolder()
    f.flag checkType { check<Boolean>() }
    f.toggle()
    f.flag checkType { check<Boolean>() }
}
