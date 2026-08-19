// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 282 -> sentence 282
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 282 -> sentence 282
 *                declarations, property-declaration -> paragraph 282 -> sentence 282
 *                declarations, property-declaration, getters-and-setters -> paragraph 282 -> sentence 282
 * NUMBER: 1
 * DESCRIPTION: a var with private set remains readable outside the class while only the class can write; covers Int, String, and Boolean; contrasts with previous-point outside write failure and with getters-and-setters p-3 declaration-only
 */

// TESTCASE NUMBER: 1
class Counter {
    var count: Int = 0
        private set

    fun inc() {
        count++
    }
}

// TESTCASE NUMBER: 2
class LabelBox {
    var label: String = "x"
        private set

    fun rename(next: String) {
        label = next
    }
}

// TESTCASE NUMBER: 3
class FlagHolder {
    var flag: Boolean = false
        private set

    fun toggle() {
        flag = !flag
    }
}

fun box(): String {
    val c = Counter()
    if (c.count != 0) return "NOK: count-0"
    c.inc()
    if (c.count != 1) return "NOK: count-1"
    c.inc()
    if (c.count != 2) return "NOK: count-2"

    val box = LabelBox()
    if (box.label != "x") return "NOK: label-x"
    box.rename("ok")
    if (box.label != "ok") return "NOK: label-ok"
    box.rename("ab")
    if (box.label != "ab") return "NOK: label-ab"

    val f = FlagHolder()
    if (f.flag) return "NOK: flag-false"
    f.toggle()
    if (!f.flag) return "NOK: flag-true"
    f.toggle()
    if (f.flag) return "NOK: flag-toggle-back"
    return "OK"
}
