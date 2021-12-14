//
// Copyright © 2021 Terry Moreland
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
// to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
// and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

package tsmoreland.objecttracker.App.controllers;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tsmoreland.objecttracker.App.models.Address;
import tsmoreland.objecttracker.App.models.LogModel;
import tsmoreland.objecttracker.App.models.ObjectAddModel;
import tsmoreland.objecttracker.App.models.ObjectModel;
import tsmoreland.objecttracker.App.models.ObjectSummaryModel;

@RestController
public class ObjectTrackerController {

    @PostMapping(
        value = "objects", 
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml" }
    )
    public ResponseEntity<?> putObject(@Validated @ModelAttribute @RequestBody ObjectAddModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(); // add new exception so we can include these in error response
        }

        var addedModel = new ObjectModel(1, "Fred", new Address(1, "Some St.", "Anyville"), Calendar.getInstance(), Collections.<LogModel>emptyList());

        return new ResponseEntity<>(addedModel, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "objects", 
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml" }
    )
    public ResponseEntity<List<ObjectSummaryModel>> getObjects() {

        return ResponseEntity.ok(List.<ObjectSummaryModel>of(
            new ObjectSummaryModel(1, "Fred"),
            new ObjectSummaryModel(2, "John")
        ));
    }

    @GetMapping(
        value = "objects/{id}", 
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml" }
    )
    public ResponseEntity<ObjectSummaryModel> getObjectById(@PathVariable int id) {
        return switch (id) {
            case 1 -> ResponseEntity.ok(new ObjectSummaryModel(1, "Fred"));
            case 2 -> ResponseEntity.ok(new ObjectSummaryModel(2, "John"));
            default -> ResponseEntity.notFound().build();
        };

    }
}