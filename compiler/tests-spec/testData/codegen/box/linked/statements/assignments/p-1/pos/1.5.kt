// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: o.x = 5 via delegated property yields x == 5 at runtime
 */

import kotlin.reflect.KProperty

class MutableDelegate(var stored: Int = 0) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = stored
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        stored = value
    }
}

class Owner {
    var x: Int by MutableDelegate()
}

// TESTCASE NUMBER: 1
fun box(): String {
    val o = Owner()
    o.x = 5
    return if (o.x == 5) "OK" else "NOK"
}
