package com.rejowan.ccpcsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rejowan.ccpc.Country
import com.rejowan.ccpc.CountryCodePicker
import com.rejowan.ccpc.ViewCustomization
import com.rejowan.ccpc.PickerCustomization
import com.rejowan.ccpcsample.ui.theme.CCPCSampleTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CCPCSampleTheme {

                ShowMainScreen()


            }
        }
    }


}

@Composable
fun ShowMainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ShowCountryCodePicker()

        ShowCCPWithTextField()

    }
}

@Composable
fun ShowCCPWithTextField() {

    var text by remember { mutableStateOf("") }

    var country by remember {
        mutableStateOf(Country.Bangladesh)
    }

    OutlinedTextField(value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 5.dp),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        label = {
            Text(
                text = "Phone Number", style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingIcon = {
            CountryCodePicker(
                selectedCountry = country,
                countryList = Country.getAllCountries(),
                onCountrySelected = {
                    country = it
                },

                )

        },
        trailingIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "drop down icon"
                )
            }
        }

    )

}


@Composable
fun ShowCountryCodePicker() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "Country Code Picker",
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 10.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )


        CountryCodePicker(
            modifier = Modifier.fillMaxWidth(1f),
            selectedCountry = Country.UnitedStates,
            onCountrySelected = {},
            viewCustomization = ViewCustomization(
                showFlag = true,
                showCountryIso = true,
                showCountryName = true,
                showCountryCode = true,
                clipToFull = true
            ),
            pickerCustomization = PickerCustomization(
                showFlag = false,
            ),
            showSheet = true,
        )


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CCPCSampleTheme {
        ShowMainScreen()
    }
}