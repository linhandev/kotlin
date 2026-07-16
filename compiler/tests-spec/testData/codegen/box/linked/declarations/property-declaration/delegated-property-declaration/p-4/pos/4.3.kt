// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: class member delegated property keeps delegate per instance
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class CounterDelegate {
    var count = 0
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = count
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) { count = value }
}

class Holder {
    var count: Int by CounterDelegate()
}

fun box(): String {
    val a = Holder()
    val b = Holder()
    a.count = 3
    b.count = 7
    return if (a.count == 3 && b.count == 7) "OK" else "NOK"
}
